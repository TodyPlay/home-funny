<template>
    <el-form :model="searchData" @submit.native.prevent @submit="flushList">
        <el-row :gutter="20">
            <el-col :span="4">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="searchData.name" placeholder="请输入名称" clearable></el-input>
                </el-form-item>
            </el-col>
            <!--用layout布局处理，支持每行多个框-->
        </el-row>
    </el-form>
    <el-button :icon="icons.search" :loading="loading" type="primary" @click="flushList" plain>
        搜索
    </el-button>
    <el-table :data="multiMediaList" stripe height="540px" max-height="540px">
        <el-table-column label="序号" width="100">
            <template #default="{$index}">
                {{ $index + 1 }}
            </template>
        </el-table-column>
        <el-table-column label="名称" prop="name" width="150"/>
        <el-table-column label="媒体类型" prop="mediaType" width="150">
            <template #default="scope">
                {{ constantsFunction.findMediaTypeByName(scope.row.mediaType)?.val }}
            </template>
        </el-table-column>
        <el-table-column label="描述文本" prop="description" width="150"/>
        <el-table-column label="标签" prop="mediaTags" width="150">
            <template #default="{row}">
                <el-tag v-for="tag in row.mediaTags.slice(0, 2)" color="-">
                    {{ tag.name }}
                </el-tag>
                <el-popover v-if="row.mediaTags.length > 2" placement="bottom" :width="row.mediaTags.length * 51">
                    <template #reference>
                        <el-link type="primary">……</el-link>
                    </template>
                    <el-tag v-for="tag in row.mediaTags" color="-">
                        {{ tag.name }}
                    </el-tag>
                </el-popover>
            </template>
        </el-table-column>
        <el-table-column label="集数" width="150x">
            <template #default="{row}">
                <span>共</span>
                <span
                    style="color: #24b29e;font-family: 黑体,serif;font-size: large;margin-left: 5px;margin-right: 5px ">{{
                        row.mediaDetails.length
                    }}</span>
                <span>集</span>
            </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
            <template #default="scope">
                <el-link type="primary" :disabled="!scope.row.mediaType" @click="open(scope.row)">
                    打开
                </el-link>

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
</template>

<script>

import {Plus, Search} from "@element-plus/icons-vue";
import {markRaw} from "vue";
import {constants, constantsFunction} from "@/constant";
import {restApi} from "@/api/restApi";

export default {
    name: "多媒体列表",
    computed: {
        constants() {
            return constants
        },
        constantsFunction() {
            return constantsFunction;
        }
    },
    components: {Search, Plus},
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
            }
        }
    },
    methods: {
        async flushList() {
            this.loading = true;
            try {
                let data = await restApi.fetch_media_list(this.searchData, this.page.page, this.page.size);
                this.multiMediaList = data.content;
                this.page.total = data.totalElements;
            } catch (e) {
                this.multiMediaList = [];
                this.page.total = 0;
                throw e;
            } finally {
                this.loading = false;
            }
        },
        open(media) {
            this.$router.push({
                path: `/media-detail-list/${media.id}/多媒体：${media.name}`,
            });
        },
    },
    watch: {},
    async mounted() {
        await this.flushList();
    }
}
</script>

<style scoped>

</style>