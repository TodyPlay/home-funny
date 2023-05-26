import axios from "axios";

axios.defaults.baseURL = "/api/v1"

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