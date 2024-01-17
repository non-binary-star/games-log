<script setup>
import {postObject} from './Utils.vue'
import {ref} from "vue";
const emit = defineEmits(['game-added'])
const API_POST_GAME_URL = `http://localhost:8080/api/game`

const game = ref(null);

function addGame() {
  postObject(`${API_POST_GAME_URL}`, {name: game.value}).then(resp => emit('game-added'));
}
</script>
<template>
  <form class="row g-3 mb-3 needs-validation" @submit.prevent="addGame" novalidate>
    <div class="col-md-4">
      <input type="text" class="form-control" placeholder="Game name" id="name" v-model="game" required>
      <div class="valid-feedback">
        Looks good!
      </div>
    </div>
    <div class="col-12">
      <button class="btn btn-primary" type="submit">Add</button>
    </div>
  </form>
</template>

<style scoped>

</style>