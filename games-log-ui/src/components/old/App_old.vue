<script setup>
import {ref} from 'vue'
import GameRow from './GameRow.vue'
import AddGame from './AddGame.vue'

const API_GET_USER_GAMES_URL = `http://localhost:8080/api/user/75e3c551-caa2-410f-b14b-3b2dfcd48606/game`
const API_GET_ALL_GAMES_URL = `http://localhost:8080/api/game`

const userGames = ref(null);
const allGames = ref(null);

loadAllGames();
loadAllUserGames();

function loadAllUserGames() {
  fetch(API_GET_USER_GAMES_URL).then(resp => resp.json()).then(json => userGames.value = json);
  console.log(userGames.value);
}

function loadAllGames() {
  fetch(API_GET_ALL_GAMES_URL).then(resp => resp.json()).then(json => allGames.value = json);
}
</script>

<template>
  <h4>Add to games db</h4>
  <AddGame @game-added="loadAllGames"/>

  <h4>Add games to your list</h4>
  <div class="container ms-0">
    <div class="row">
      <div class="col-12 mb-3 fw-bold">Name</div>
    </div>
    <div class="row mb-3" v-for="game in allGames">
      <GameRow @entry-added="loadAllUserGames" :game="game"/>
    </div>
  </div>

  <h4>My Games</h4>
  <table class="table table-bordered">
    <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">State</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="entry in userGames">
      <td>{{ entry.gameName }}</td>
      <td>{{ entry.state }}</td>
    </tr>
    </tbody>
  </table>
</template>
