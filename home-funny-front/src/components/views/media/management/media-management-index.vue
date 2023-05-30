<template><div>
    <el-form :model="searchData" @submit.native.prevent @submit="flushList">
        <el-row :gutter="20">
            <el-col :span="5">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="searchData.name" placeholder="请输入名称" clearable></el-input>
                </el-form-item>
            </el-col>
            <!--用layout布局处理，支持每行多个框-->
        </el-row>
    </el-form>

    <el-button type="primary" @click="flushList" :loading="loading" :icon="Search" plain>查询</el-button>
    <el-button type="primary" @click="newMedia" plain>新增</el-button>

    <el-table :data="multiMediaList" stripe height="540px" max-height="540px">
        <el-table-column label="ID" prop="id" width="50">
        </el-table-column>
        <el-table-column label="名称" prop="name" width="150"/>
        <el-table-column label="详情" prop="description" width="150"/>
        <el-table-column label="封面" width="80">
            <template #default="scope">
                <el-link type="primary" :disabled="!scope.row.coverStorage" @click="evt => scope.row.viewer = true"
                         :title="scope.row.coverStorage ? '点击预览封面' : '暂未上传封面'">预览
                </el-link>
                <el-image-viewer v-if="scope.row.viewer"
                                 @close="scope.row.viewer = false"
                                 hide-on-click-modal
                                 teleported
                                 :url-list="[`${restApi.minio}/${scope.row.coverStorage.storageGroup}/${scope.row.coverStorage.storagePath}`]"
                ></el-image-viewer>
            </template>
        </el-table-column>
        <el-table-column label="媒体类型" prop="mediaType" width="150">
            <template #default="scope">
                {{ constantsFunction.findMediaTypeByName(scope.row.mediaType)?.val }}
            </template>
        </el-table-column>
        <el-table-column label="标签" width="150">
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
        <el-table-column label="集数" width="150">
            <template #default="{row}">
                <span>共</span>
                <span
                    style="color: #24b29e;font-family: 黑体,serif;font-size: large;margin-left: 5px;margin-right: 5px ">
                    {{ row.mediaDetails.length }}
                </span>
                <span>集</span>
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template #default="scope">
                <el-link type="primary" @click="editeMedia(scope.row.id)">编辑</el-link>
                <el-divider direction="vertical"></el-divider>
                <el-popconfirm title="确定要删除该数据吗，此操作不可逆!" width="280"
                               @confirm="removeMedia(scope.row.id)"
                >
                    <template #reference>
                        <el-link type="danger">删除</el-link>
                    </template>
                </el-popconfirm>
            </template>
        </el-table-column>

    </el-table>

    <el-pagination v-model:current-page="paging.page"
                   v-model:disabled="loading"
                   v-model:page-size="paging.size"
                   v-model:page-sizes="paging.pageSizes"
                   v-model:total="paging.total"
                   background
                   layout="total, sizes, jumper, ->, prev, pager, next"
                   @current-change="flushList"
                   @size-change="flushList"
    />
</div></template>

<script>
import {constants, constantsFunction} from "@/constant";
import {Search} from "@element-plus/icons-vue";
import {restApi} from "@/api/restApi";

export default {
    name: "多媒体管理",
    computed: {
        restApi() {
            return restApi
        },
        constantsFunction() {
            return constantsFunction
        },
        Search() {
            return Search
        },
        constants() {
            return constants
        }
    },
    data() {
        return {
            loading: false,
            searchData: {
                name: ""
            },
            multiMediaList: [],
            paging: {
                pageSizes: [10, 20, 50, 100, 200, 500],
                page: 1,
                size: 20,
                total: 0,
                orders: [],
            }
        };
    },
    methods: {
        async flushList() {
            this.loading = true;
            try {
                let data = await restApi.fetch_media_list(this.searchData, this.paging.page, this.paging.size);
                this.multiMediaList = data.content;
                this.paging.total = data.totalElements;
            } catch (e) {
                this.multiMediaList = [];
                this.paging.total = 0;
                throw e;
            } finally {
                this.loading = false;
            }

        },
        editeMedia(id) {
            this.$router.push({
                path: "/media-management-edite",
                query: {
                    id: id
                }
            })
        },
        async removeMedia(id) {
            let result = await restApi.delete_media(id);
            this.$message.success("删除成功");
            await this.flushList();
        },
        newMedia() {
            this.$router.push({
                path: "/media-management-edite",
            })
        }
    },
    async mounted() {
        await this.flushList();
    }
}
</script>

<style scoped>

</style>