import {createRouter, createWebHashHistory} from "vue-router";
import {Grid, List} from "@element-plus/icons-vue";

//首页
import index from "@/components/Index.vue";

//媒体播放
import MediaListIndex from "@/components/media-list/MediaIndex.vue";
import MediaDetailList from "@/components/media-list/MediaDetailList.vue"

//存储
import StorageIndex from "@/components/storage/StorageIndex.vue";

//媒体管理
import MediaManagementIndex from "@/components/media-management/MediaManagementIndex.vue"
import MediaManagementEdite from "@/components/media-management/MediaManagementEdite.vue";

const routes = [
    {
        icon: Grid,
        path: "/",
        component: index,
        name: index.name,
    },
    {
        icon: List,
        path: '/media-index',
        component: MediaListIndex,
        name: MediaListIndex.name,
    },
    {
        inMenu: false,
        icon: Grid,
        path: "/media-detail-list/:id/:tagName",
        component: MediaDetailList,
        name: MediaDetailList.name,
    },
    {
        icon: Grid,
        path: "/media-management",
        component: MediaManagementIndex,
        name: MediaManagementIndex.name,
    },
    {
        inMenu: false,
        icon: Grid,
        path: "/media-management-edite",
        component: MediaManagementEdite,
        name: MediaManagementEdite.name,
    },
    {
        icon: List,
        path: "/storage-list",
        component: StorageIndex,
        name: StorageIndex.name,
    },
]


let router = createRouter({
    history: createWebHashHistory(), routes: routes
});

export {router, routes};