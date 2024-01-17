<script setup>
import {postObject} from "./Utils.vue";

const API_POST_USER_GAME_URL = `http://localhost:8080/api/user/75e3c551-caa2-410f-b14b-3b2dfcd48606/game`

const props = defineProps(['state', 'game']);
const emit = defineEmits(['entry-added'])

function addGameEntry() {
  postObject(`${API_POST_USER_GAME_URL}/${props.game.id}`, {state: props.state}).then(resp => emit('entry-added'));
}
</script>
<template>
  <li><a class="dropdown-item" href="#" @click="addGameEntry()">{{ state }}</a></li>
</template>

<style scoped>
</style>