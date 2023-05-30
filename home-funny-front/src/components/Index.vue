<template>
    <div>
        <h1>待办</h1>
        <p v-for="(item ,index) in todoList">{{ index + 1 + " , " + item }}</p>
    </div>
</template>

<script>
import {todoList} from "@/todoList"

export default {
    name: "首页",
    data() {
        return {
            todoList: todoList
        };
    }
}
</script>


<style scoped>

</style>