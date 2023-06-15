<template>
    <div>
        <el-form v-model="searchData" @submit="flushList" @submit.native.prevent>
            <el-row>
                <el-col :span="5">
                    <el-form-item label="文件名称">
                        <el-input v-model="searchData.name"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-button type="primary" native-type="submit" :loading="loading" :icon="Search" plain>查询</el-button>
            <el-button type="warning" :icon="upload" @click="evt => this.$uploader.uploadSingle()">快速上传</el-button>

        </el-form>
        <el-table :data="tableList" height="540px" max-height="540px">
            <el-table-column label="ID" prop="id" width="80px"/>
            <el-table-column label="文件名称" prop="storageName"/>
            <el-table-column label="文件分组" prop="storageGroup" width="150px"/>
            <el-table-column label="文件路径" prop="storagePath"/>
            <el-table-column label="操作" align="right">
                <template #default="scope">
                    <el-popconfirm title="确定要删除该文件吗，此操作不可逆!" width="280"
                                   @confirm="remove(scope.row)"
                    >
                        <template #reference>
                            <el-link type="danger">
                                <el-icon>
                                    <delete/>
                                </el-icon>
                                删除
                            </el-link>
                        </template>
                    </el-popconfirm>
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
                       @size-change="flushList"
                       @current-change="flushList"
        />
    </div>
</template>

<script>
import {Delete, Search, Upload} from "@element-plus/icons-vue";
import {restApi} from "@/api/restApi";

export default {
    name: "StorageList",
    components: {Upload, Delete},
    computed: {
        Search() {
            return Search
        },
        upload() {
            return Upload;
        }
    },
    data() {
        return {
            tableList: [],
            loading: false,
            searchData: {
                name: ""
            },
            page: {
                pageSizes: [10, 20, 50, 100, 200, 500],
                page: 1,
                size: 20,
                total: 0,
                orders: [],
            },
        };
    },
    methods: {
        async flushList() {
            this.loading = true;
            try {
                let data = await restApi.fetch_storage_list(this.searchData, this.page.page, this.page.size);
                this.tableList = data.content;
                this.page.total = data.totalElements;
            } catch (e) {
                this.tableList = [];
                this.page.total = 0;
                throw e;
            } finally {
                this.loading = false;
            }
        },
        async remove(row) {
            await restApi.delete_storage(row.id);
            this.$message.success("删除成功：" + row.id);
            await this.flushList();
        }
    },
    async mounted() {
        await this.flushList();
    }
}
</script>

<style scoped>

</style>