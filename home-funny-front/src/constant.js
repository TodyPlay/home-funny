import {restApi} from "@/api/restApi";

let constants = {
    /**
     * 所有标签列表
     */
    tags: await restApi.fetch_dic('media-tags'),
    /**
     * 所有类型列表
     */
    types: await restApi.fetch_const("media-types"),
    /**
     * 支持的图片格式常见后缀
     */
    imageSuffix: [".apng", ".bmp", ".gif", ".ico", ".cur", ".jpg", ".jpeg", ".jfif", ".pjpeg", ".pjp", ".png", ".png", ".webp"],
}

let constantsFunction = {
    findMediaTypeByName: (name) => {
        return constants.types.filter(v => v.key === name)[0];
    }
};


export {constants, constantsFunction}