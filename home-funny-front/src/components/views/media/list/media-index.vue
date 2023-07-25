<template>
    <div>
        <el-form :model="searchData" @submit.native.prevent @submit="flushList">
            <el-row :gutter="20">
                <el-col :span="4">
                    <el-form-item label="名称" prop="name">
                        <el-input v-model="searchData.name" placeholder="请输入名称" clearable></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <el-button :icon="icons.search" :loading="loading" type="primary" @click="flushList" plain>
            搜索
        </el-button>
        <el-table :data="multiMediaList" stripe height="620px" max-height="620px">
            <el-table-column>
                <template #default="{row, $index}">
                    <el-row>
                        <el-col :span="4">
                            <el-image style="height: 144px; width: 220px; border-radius: 12px"
                                      :src="row.coverStorage ? `${restApi.minio}/${row.coverStorage.storageGroup}/${row.coverStorage.storagePath}` : unsavedImg"/>
                        </el-col>
                        <el-col :span="15">
                            <el-row>
                                <el-col :span="3">
                                    <span style="font-family: 微软雅黑,serif; font-size: 17px">
                                        {{ row.name }}
                                    </span>
                                </el-col>
                                <el-col :span="1">
                                    <el-tag>{{ types.filter(k => k.key === row.mediaType)[0]?.val }}</el-tag>
                                </el-col>
                            </el-row>
                            <el-row style="height: 15px">

                            </el-row>
                            <el-row>
                                <el-col :span="2">
                                    <span>共</span>
                                    <span
                                        style="color: #24b29e;font-family: 黑体,serif;font-size: large;margin-left: 5px;margin-right: 5px ">{{
                                            row.mediaDetails.length
                                        }}</span>
                                    <span>集</span>
                                </el-col>
                                <el-col :span="6">
                                    <el-tag type="success">更新时间: {{ row.lastModifiedDate }}</el-tag>
                                </el-col>
                            </el-row>
                            <el-row style="height: 45px">

                            </el-row>
                            <el-row>
                                <el-col>
                                    <el-link type="primary" :disabled="!row.mediaType" @click="open(row)">
                                        <el-icon>
                                            <top-right/>
                                        </el-icon>
                                        <span style="font-size: 16px">打开</span>
                                    </el-link>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>

                </template>
            </el-table-column>
        </el-table>

        <el-pagination v-model:current-page="page.page"
                       v-model:disabled="loading"
                       v-model:page-size="page.size"
                       v-model:page-sizes="page.pageSizes"
                       v-model:total="page.total"
                       background
                       layout="total, sizes, jumper, ->, prev, pager, next"
                       @current-change="flushList"
                       @size-change="flushList"
        />
    </div>
</template>

<script>

import {Plus, Search, TopRight} from "@element-plus/icons-vue";
import {markRaw} from "vue";
import {restApi} from "@/api/restApi";
import unsaved from '@/assets/unsaved.png';

export default {
    name: "MediaIndex",
    computed: {
        restApi() {
            return restApi
        },
        unsavedImg() {
            return unsaved;
        }
    },
    components: {TopRight, Search, Plus},
    data() {
        return {
            multiMediaList: [],
            searchData: {
                name: "",
            },
            loading: false,
            icons: {
                search: markRaw(Search),
                plus: markRaw(Plus),
            },
            page: {
                pageSizes: [10, 20, 50, 100, 200, 500],
                page: 1,
                size: 20,
                total: 0,
                orders: [],
            },
            types: []
        }
    },
    methods: {
        flushList() {
            this.loading = true;
            restApi.fetch_media_list(this.searchData, this.page.page, this.page.size)
                .then(
                    ok => {
                        this.multiMediaList = ok.content;
                        this.page.total = ok.totalElements;
                    },
                    err => {
                        this.multiMediaList = [];
                        this.page.total = 0;
                        return Promise.reject(err);
                    }
                ).finally(() => this.loading = false);
        },
        open(media) {
            this.$router.push({
                path: `/media-detail-list/${media.id}/多媒体：${media.name}`,
            });
        },
    },
    watch: {},
    mounted() {
        restApi.fetch_const("media-types")
            .then(
                ok => {
                    this.types = ok;
                    this.flushList();
                }
            )
    }
}
</script>

<style scoped>

</style>