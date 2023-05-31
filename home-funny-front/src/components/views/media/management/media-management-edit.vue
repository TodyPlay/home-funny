<template><div>
    <el-form ref="form" :model="multiMedia" :rules="rules" @submit="submit" :disabled="loading" @submit.native.prevent>
        <el-row :gutter="20">
            <el-col :span="4">
                <el-form-item label="多媒体名称" prop="name">
                    <el-input v-model="multiMedia.name" clearable></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="4">
                <el-form-item label="多媒体类型" prop="mediaType">
                    <el-select v-model="multiMedia.mediaType">
                        <el-option v-for="type in constants.types" :key="type.key" :label="type.val"
                                   :value="type.key"/>
                    </el-select>
                </el-form-item>
            </el-col>
            <el-col :span="4">
                <el-form-item label="标签" prop="mediaTags">
                    <el-select v-model="multiMedia.mediaTags"
                               collapse-tags filterable multiple clearable value-key="name">
                        <el-option v-for="tag in constants.tags" :key="tag.name" :label="tag.name"
                                   :value="tag"/>
                    </el-select>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col>
                <el-form-item label="封面" prop="coverStorage">
                    <el-upload :http-request="upload" :show-file-list="false"
                               :on-success="(resp) => this.multiMedia.coverStorage = resp"
                               :on-error="error => $message.error(error)"
                               :accept="constants.imageSuffix"
                    >
                        <el-link type="primary" class="cover">上传封面</el-link>
                    </el-upload>

                    <el-link type="primary" class="cover"
                             :disabled="!multiMedia.coverStorage"
                             @click="evt => this.multiMedia.coverStorage = null"
                    >
                        删除封面
                    </el-link>
                    <el-link type="primary" class="cover"
                             :disabled="!multiMedia.coverStorage"
                             @click="evt => {viewer = true}"
                    >
                        预览封面
                    </el-link>
                    <el-image-viewer v-if="viewer"
                                     @close="viewer = false"
                                     hide-on-click-modal
                                     teleported
                                     :url-list="[`${restApi.minio}/${this.multiMedia.coverStorage.storageGroup}/${this.multiMedia.coverStorage.storagePath}`]"
                    ></el-image-viewer>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col>
                <el-form-item label="描述文本" prop="description">
                    <el-input type="textarea" :autosize="{minRows:2,maxRows:2}" v-model="multiMedia.description">
                    </el-input>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col>
                <el-form-item label="详情列表" prop="mediaDetails">
                    <el-link type="primary" @click="multiMedia.mediaDetails.push({})">点击添加</el-link>
                    <el-table :data="multiMedia.mediaDetails"
                              size="small" height="480" max-height="480" stripe
                    >
                        <el-table-column label="序号" width="60">
                            <template #default="{$index}">
                                {{ $index + 1 }}
                            </template>
                        </el-table-column>
                        <el-table-column label="名称" width="180">
                            <template #default="{row}">
                                <el-input size="small" v-model="row.detailName"/>
                            </template>
                        </el-table-column>
                        <el-table-column label="媒体类型" width="180">
                            <template #default="{row}">
                                <el-select size="small" v-model="row.mediaType">
                                    <el-option v-for="type in constants.types" :key="type.key" :label="type.val"
                                               :value="type.key"/>
                                </el-select>
                            </template>
                        </el-table-column>
                        <el-table-column label="文件" width="200">
                            <template #default="{row}">
                                <el-row>
                                    <el-upload :http-request="upload" :show-file-list="false"
                                               :on-success="(resp) => row.storage = resp"
                                               :on-progress="(evt, uploadFile, uploadFiles) => console.log(evt, uploadFile, uploadFiles)"
                                               :on-error="error => $message.error(error)"
                                    >
                                        <el-link type="primary" class="cover">上传文件</el-link>
                                    </el-upload>
                                    <el-link type="primary" class="cover"
                                             :disabled="!row.storage"
                                             @click="evt => row.storage = null"
                                    >
                                        删除文件
                                    </el-link>
                                </el-row>
                            </template>
                        </el-table-column>
                        <el-table-column label="排序操作" width="150">
                            <template #default="{$index}">
                                <el-row>
                                    <el-link type="success" title="点击上移一行"
                                             :disabled="$index === 0"
                                             @click="handleMove('UP', $index)">
                                        <el-icon>
                                            <arrow-up/>
                                        </el-icon>
                                        上移
                                    </el-link>
                                    <el-divider direction="vertical"/>
                                    <el-link type="warning" title="点击下移一行"
                                             :disabled="$index === multiMedia.mediaDetails.length - 1"
                                             @click="handleMove('DOWN', $index)">
                                        <el-icon>
                                            <arrow-down/>
                                        </el-icon>
                                        下移
                                    </el-link>
                                </el-row>
                            </template>
                        </el-table-column>
                        <el-table-column>
                            <template #default="{$index}">
                                <el-link type="danger" title="点击删除该行"
                                         @click="evt =>  multiMedia.mediaDetails.splice($index, 1)">
                                    <el-icon>
                                        <delete-filled/>
                                    </el-icon>
                                </el-link>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-form-item>
            </el-col>
        </el-row>
        <el-row>
            <el-col>
                <el-button type="primary" :loading="loading" :icon="select" native-type="submit" plain>保存</el-button>
                <el-popconfirm title="确定要重置吗，所有修改将丢失!" width="280"
                               @confirm="reset"
                >
                    <template #reference>
                        <el-button type="primary" :loading="loading" :icon="refreshRight" plain>重置</el-button>
                    </template>
                </el-popconfirm>
            </el-col>
        </el-row>
    </el-form>
</div></template>


<script>

import {constants} from "@/constant";
import {ArrowDown, ArrowUp, DeleteFilled, Plus, RefreshRight, Select} from "@element-plus/icons-vue";
import {restApi} from "@/api/restApi";

export default {
    name: "MediaManagementEdite",
    components: {DeleteFilled, ArrowDown, ArrowUp, Plus, Select, RefreshRight},
    computed: {
        restApi() {
            return restApi;
        },
        constants() {
            return constants
        },
        select() {
            return Select;
        },
        refreshRight() {
            return RefreshRight;
        }
    },
    data() {
        return {
            loading: false,
            multiMedia: {
                name: "",
                mediaType: "VIDEO",
                description: "",
                mediaDetails: [],
                mediaTags: [],
            },
            viewer: false,
            rules: {
                name: [{required: true, message: "名称是必填的"}],
                description: [{max: 128, message: "最长限制128个字符"}],
                mediaType: [{required: true, message: "类型必填"}]
            }
        };
    },
    methods: {
        handleMove(direction, index) {
            switch (direction) {
                case  'UP':
                    [this.multiMedia.mediaDetails[index], this.multiMedia.mediaDetails[index - 1]] =
                        [this.multiMedia.mediaDetails[index - 1], this.multiMedia.mediaDetails[index]];
                    break;
                case 'DOWN':
                    [this.multiMedia.mediaDetails[index], this.multiMedia.mediaDetails[index + 1]] =
                        [this.multiMedia.mediaDetails[index + 1], this.multiMedia.mediaDetails[index]];
                    break;
                default:
                    break;
            }
        },
        async submit() {

            let result = await this.$refs.form.validate((isValid, invalidFields) => {
            });

            if (!result) {
                return;
            }

            this.loading = true;

            try {
                this.multiMedia = await restApi.put_media(this.multiMedia);
                this.$message.success("保存成功");
            } finally {
                this.loading = false;
            }

        },
        async upload(options) {
            return await restApi.put_storage(options.file);
        },
        d_multiMedia() {
            return {
                name: "",
                mediaType: "VIDEO",
                description: "",
                mediaDetails: [],
                mediaTags: [],
            };
        },
        async reset() {
            let id = this.$route.query.id;

            this.multiMedia = (id ? await restApi.fetch_media(id) : this.d_multiMedia());

            this.$message.success("已重置")
        }
    },
    async activated() {
        let id = this.$route.query.id;

        if (id) {
            id = parseInt(id);
        }

        if (!id && this.multiMedia.id) {
            this.multiMedia = this.d_multiMedia();
        }

        if (id && this.multiMedia.id !== id) {
            this.multiMedia = await restApi.fetch_media(id);
        }
    }
}

</script>


<style scoped>
.cover {
    margin-left: 10px;
}
</style>