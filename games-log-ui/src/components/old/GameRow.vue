<script setup>
import Dropdown from './Dropdown.vue'

const props = defineProps(['game']);

</script>

<template>
  <div class="col-1">
    {{ game.name }}
  </div>
  <div class="col-3">
    <button class="btn btn-secondary dropdown-toggle" type="button"
            data-bs-toggle="dropdown" aria-expanded="false">
      <span>Add to my list</span>
    </button>
    <ul class="dropdown-menu">
      <Dropdown state="Planning" @entry-added="$emit('entry-added')" :game="game"/>
      <Dropdown state="Playing" @entry-added="$emit('entry-added')" :game="game"/>
      <Dropdown state="Completed" @entry-added="$emit('entry-added')" :game="game"/>
      <Dropdown state="Dropped" @entry-added="$emit('entry-added')" :game="game"/>
    </ul>
  </div>
</template>
<style scoped>

</style>