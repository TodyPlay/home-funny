<template>
    <div>
        <h1>待办</h1>
        <li v-for="(item ,index) in todoList">
            <span style="font-family: '幼圆',serif ;color: #177abb">{{ item }}</span>
        </li>
    </div>
</template>

<script>
import {todoList} from "@/todoList"

export default {
    name: "Index",
    data() {
        return {
            todoList: todoList
        };
    }
}
</script>


<style scoped>

</style>