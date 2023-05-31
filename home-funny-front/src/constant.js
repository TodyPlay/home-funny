let constants = {
    /**
     * 支持的图片格式常见后缀
     */
    imageSuffix: [".apng", ".bmp", ".gif", ".ico", ".cur", ".jpg", ".jpeg", ".jfif", ".pjpeg", ".pjp", ".png", ".png", ".webp"],
}

let lazy_constants = {
    tags: [],
    types: [],
}


let constantsFunction = {
    findMediaTypeByName: (name) => {
        return lazy_constants.types.filter(v => v.key === name)[0];
    }
};

export {constants, lazy_constants, constantsFunction}