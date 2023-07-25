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
        return window.showOpenFilePicker({multiple: false})
            .then(
                handlers => {
                    return handlers[0].getFile();
                })
            .then(
                file => {
                    return restApi.put_storage(file, env => {
                        console.log(env.progress);
                    });
                })
            .then(
                ok => {
                    ElMessage.success("上传成功");
                    return Promise.resolve(ok);
                },
                err => {
                    ElMessage.error("上传失败");
                    return Promise.reject(err);
                }
            );
    }
}
export {uploader}