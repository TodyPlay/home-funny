import {createApp} from 'vue'
import App from './App.vue'
import './assets/main.css'

import ElementPlus, {ElMessage} from 'element-plus'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import 'element-plus/dist/index.css'
import {router} from "@/router";
import mitt from "mitt";
import Cookies from "js-cookie";

let app = createApp(App);

app.config.errorHandler = err => {
    ElMessage.error(err.message);
};

app.config.globalProperties.$bus = mitt();

app.config.globalProperties.$cookies = Cookies;


app.use(ElementPlus, {
    locale: zhCn
});

app.use(router);

app.mount('#app');