import axios from "axios";
import {ElMessage} from "element-plus";
import {router} from "@/router";

axios.defaults.baseURL = "/api/v1"
axios.defaults.withCredentials = true;
axios.interceptors.response.use(
    resp => {
        return Promise.resolve(resp.data);

    },
    error => {
        if (error.response?.status === 401 && error.config.url !== "/login") {
            ElMessage.error("请先登录");
            setTimeout(() => {
                router.push({
                    path: "/login"
                }).then(value => {
                    Cookies.remove("Token");
                });
            })
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
    login: "/login",
    logout: "/logout",
    fetch_const: async (constantType) => {
        return (await axios.get(restApi.constant + "/" + constantType));
    },
    fetch_dic: async (dicName) => {
        return (await axios.get(restApi.dictionary + "/" + dicName));
    },
    put_dic: async (dicName, data) => {
        return (await axios.put(restApi.dictionary + "/" + dicName, data));
    },
    delete_dic: async (dicName, id) => {
        return (await axios.delete(restApi.dictionary + "/" + dicName + "/" + id));
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
        return (await axios.get(restApi.multiMedia + "/" + id));
    },
    fetch_media_list: async (body, page, size) => {
        if (page && size) {
            return (await axios.post(`${restApi.multiMedia}?page=${page - 1}&size=${size}`, body));
        }

        return (await axios.post(restApi.multiMedia, body));
    },
    put_media: async (body) => {
        return (await axios.put(restApi.multiMedia, body));
    },
    delete_media: async (id) => {
        return (await axios.delete(restApi.multiMedia + "/" + id));
    },
    fetch_storage_list: async (body, page, size) => {
        if (page && size) {
            return (await axios.post(`${restApi.storage}?page=${page - 1}&size=${size}`, body));
        }

        return (await axios.post(restApi.storage, body));
    },
    put_storage: async (file, process) => {

        let formData = new FormData();
        formData.append('file', file);

        return (await axios.put(
            restApi.storage,
            formData,
            {
                headers: {"Content-Type": "multipart/form-data"},
                onUploadProgress: process
            }
        ))
    },
    delete_storage: async (id) => {
        return (await axios.delete(restApi.storage + "/" + id));
    },
    do_login: async (username, password) => {
        return (await axios.post(restApi.login, {username, password}));
    },
    do_login_out: async () => {
        return (await axios.post(restApi.logout));
    }
}

export {restApi}