import {restApi} from "@/api/restApi";

let formatter = new Intl.NumberFormat("zh-CN",
    {
        style: "percent"

    }
)


let uploader = {
    uploadSingle: async () => {
        let fileSystemFileHandles = await window.showOpenFilePicker({multiple: false});
        console.assert(fileSystemFileHandles.length === 1);
        let file = await fileSystemFileHandles[0].getFile();
        return await restApi.put_storage(file, env => {
            console.log(env.progress);
        });

    }
}
export {uploader}