import {createApp} from 'vue'
import App from './App.vue'
import './assets/main.css'

import ElementPlus, {ElMessage} from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import 'element-plus/dist/index.css'
import {router} from "@/router";
import mitt from "mitt";

let app = createApp(App);

app.config.errorHandler = err => {
    console.error(err);
    ElMessage.error(err.message);

    if (err.response?.data?.message) {
        ElMessage.error(err.response.data.message);
    }
};

app.config.globalProperties.$bus = mitt();


app.use(ElementPlus, {
    locale: zhCn
});

app.use(router);

app.mount('#app');