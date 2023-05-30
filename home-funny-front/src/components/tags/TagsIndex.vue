<template><div>
    <el-form @submit.native.prevent @submit="save">
        <el-row :gutter="20">
            <el-col :span="3">
                <el-form-item label="名称">
                    <el-input ref="quick" v-model="quickInsert" placeholder="输入标签名"></el-input>
                </el-form-item>
            </el-col>
            <el-col :span="1">
                <el-button :icon="plus" type="success" native-type="submit" title="回车快速添加" plain></el-button>
            </el-col>
        </el-row>
    </el-form>

    <el-divider/>
    <el-button type="primary" @click="flushList" plain>查询</el-button>
    <el-table :data="tagList" size="small" :max-height="768">
        <el-table-column label="ID" prop="id"/>
        <el-table-column lable="名称" prop="name"/>
        <el-table-column label="操作">
            <template #default="{row}">
                <el-link type="danger" @click="remove(row.id)">删除</el-link>
            </template>
        </el-table-column>
    </el-table>

</div></template>

<script>
import {restApi} from "@/api/restApi";
import {Plus} from "@element-plus/icons-vue";

export default {
    name: "标签管理",
    computed: {
        restApi() {
            return restApi
        },
        plus() {
            return Plus;
        }
    },
    data() {
        return {
            tagList: [],
            quickInsert: "",
        };
    },
    methods: {
        async flushList() {
            this.tagList = await restApi.fetch_dic("media-tags")
        },
        async save() {

            if (this.quickInsert.trim().length === 0) {
                return this.$message.warning("先输入数据")
            }

            try {
                await restApi.put_dic_media_tag({name: this.quickInsert.trim()});
                this.$message.success("保存成功");
                this.quickInsert = "";
                await this.flushList();
            } catch (e) {
                this.$message.error("保存失败");
                this.$message.error(e.message);
                this.$message.error(e.response?.data?.message);
            }
        },

        async remove(id) {
            await restApi.delete_dic_media_tag(id);
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