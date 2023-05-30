<template>
    <div>
        <div v-for="(media , id) in medias" v-show="id === current">

            <el-row>
                <el-col :span="5">
                    <el-table :data="media.mediaDetails" max-height="730" height="730" size="small" stripe>
                        <el-table-column label="序列">
                            <template #default="scope">
                                {{ scope.$index + 1 }}
                            </template>
                        </el-table-column>
                        <el-table-column prop="detailName" label="名称"></el-table-column>
                        <el-table-column label="媒体类型" prop="mediaType">
                            <template #default="scope">
                                {{ constantsFunction.findMediaTypeByName(scope.row.mediaType)?.val ?? '未选择' }}
                            </template>
                        </el-table-column>
                        <el-table-column label="操作">
                            <template #default="{ row, column, $index }">
                                <el-link type="primary" :disabled="row.storage == null"
                                         @click="evt => currentVideo[id] = row">播放
                                </el-link>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-col>
                <el-col :span="17">
                    <div class="div-video">
                        <video :id="'video_' + id"
                               v-if="currentVideo[id]"
                               :src="`${restApi.minio}/${currentVideo[id].storage.storageGroup}/${currentVideo[id].storage.storagePath}`"
                               type="video/mp4"
                               controls>
                        </video>
                        <el-empty v-else description="选择一个视频打开" style="top: 200px">
                        </el-empty>
                    </div>
                </el-col>
            </el-row>
        </div>

    </div>
</template>

<script>
import {constants, constantsFunction} from "@/constant";
import {restApi} from "@/api/restApi";

export default {
    name: "多媒体详情",
    computed: {
        constantsFunction() {
            return constantsFunction
        },
        constants() {
            return constants
        },
        restApi() {
            return restApi;
        }
    },
    data() {
        return {
            current: null,
            currentVideo: {},
            medias: {},
        };
    },
    async activated() {
        let id = this.$route.params?.id;
        if (id) {
            this.current = id;
            if (!this.medias[id]) {
                this.medias[id] = await restApi.fetch_media(id);
            }
        }
    },
    deactivated() {
        this.current = null;
    },
    watch: {
        async '$route.params.id'(newId, oldId) {
            if (oldId) {
                let id = "video_" + oldId;
                let video = document.getElementById(id);
                if (video && !video.paused) {
                    video.pause();
                }
            }

            if (newId) {
                if (!this.medias[newId]) {
                    this.medias[newId] = await restApi.fetch_media(newId);
                }
                this.current = newId;
            }
        }
    },
    methods: {
        onTabClose(val) {
            if (val.path.startsWith("/media-detail-list")) {
                let id = val.params.id
                delete this.medias[id];
                delete this.currentVideo[id];
            }
        },
    },
    beforeMount() {
        this.$bus.on("tab-close", this.onTabClose);
    },
    unmounted() {
        this.$bus.off("tab-close", this.onTabClose);
    }

}
</script>

<style scoped>

video {
    width: calc(1920px / 1.6);
    height: calc(1080px / 1.6);
}

</style>