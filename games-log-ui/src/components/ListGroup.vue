<script setup>
defineProps(['label', 'entries'])
</script>
<template>
  <h4>{{ label }}</h4>
  <table class="table table-hover">
    <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Title</th>
      <th scope="col">State</th>
      <!--            <th scope="col">Time</th>-->
    </tr>
    </thead>
    <tbody>
    <tr v-for="(entry, index) in entries">
      <td>{{ index + 1 }}</td>
      <td>{{ entry.game.name }}</td>
      <td>{{ entry.label }}</td>
    </tr>
    </tbody>
  </table>
</template>

<style scoped>
th, td {
  width: 33.333%;
}
</style>