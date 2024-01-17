<script setup>

const props = defineProps(['name', 'isActive']);

</script>
<template>
    <a :class="{ 'active': !!isActive }" class="list-group-item list-group-item-action"
       data-toggle="list" >{{ name }}</a>
</template>