import {restApi} from "@/api/restApi";
import {ElMessage} from "element-plus";

let formatter = new Intl.NumberFormat("zh-CN",
    {
        style: "percent"

    }
)

let time = 0;


let uploader = {
    uploadSingle: () => {
        return window.showOpenFilePicker({multiple: false}).then(handlers => {
            return handlers[0].getFile();
        }).then(file => {
            return restApi.put_storage(file, env => {
                console.log(env.progress);
            });
        }).finally(() => {
                ElMessage.success("上传成功" + ++time);
            }
        );
    }
}
export {uploader}