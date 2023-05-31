import axios from "axios";
import {ElMessage} from "element-plus";
import {router} from "@/router";

axios.defaults.baseURL = "/api/v1"
axios.interceptors.response.use(
    resp => {
        return Promise.resolve(resp);

    },
    error => {

        switch (error.response.status) {
            case 401:
                ElMessage.error("未经授权");
                return router.push({
                    path: "/login",
                });
            case  403:
                ElMessage.warning("授权过期");
                return router.push({
                    path: "/login",
                });
            default:
                ElMessage.error(error.message);
                break;
        }

        return Promise.reject(error);
    }
)

const mediaTags = "media-tags";

let restApi = {
    constant: "/constant",
    dictionary: "/dictionary",
    multiMedia: "/multi-media",
    multiMediaDetail: "/multi-media-detail",
    storage: "/storage",
    minio: "/minio",
    fetch_const: async (constantType) => {
        return (await axios.get(restApi.constant + "/" + constantType)).data;
    },
    fetch_dic: async (dicName) => {
        return (await axios.get(restApi.dictionary + "/" + dicName)).data;
    },
    put_dic: async (dicName, data) => {
        return (await axios.put(restApi.dictionary + "/" + dicName, data)).data;
    },
    delete_dic: async (dicName, id) => {
        return (await axios.delete(restApi.dictionary + "/" + dicName + "/" + id)).data;
    },
    fetch_dic_media_tag: async () => {
        return await restApi.fetch_dic(mediaTags);
    },
    put_dic_media_tag: async (data) => {
        return await restApi.put_dic(mediaTags, data);
    },
    delete_dic_media_tag: async (id) => {
        return await restApi.delete_dic(mediaTags, id);
    },
    fetch_media: async (id) => {
        return (await axios.get(restApi.multiMedia + "/" + id)).data;
    },
    fetch_media_list: async (body, page, size) => {
        if (page && size) {
            return (await axios.post(`${restApi.multiMedia}?page=${page - 1}&size=${size}`, body)).data;
        }

        return (await axios.post(restApi.multiMedia, body)).data;
    },
    put_media: async (body) => {
        return (await axios.put(restApi.multiMedia, body)).data;
    },
    delete_media: async (id) => {
        return (await axios.delete(restApi.multiMedia + "/" + id)).data;
    },
    fetch_storage_list: async (body, page, size) => {
        if (page && size) {
            return (await axios.post(`${restApi.storage}?page=${page - 1}&size=${size}`, body)).data;
        }

        return (await axios.post(restApi.storage, body)).data;
    },
    put_storage: async (file) => {

        let formData = new FormData();
        formData.append('file', file);

        return (await axios.put(
            restApi.storage,
            formData,
            {headers: {"Content-Type": "multipart/form-data"}}
        )).data
    },
    delete_storage: async (id) => {
        return (await axios.delete(restApi.storage + "/" + id)).data;
    },
}

export {restApi}