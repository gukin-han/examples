<template>
  <v-container>
    <v-row justify="center">
      <v-col md="4">
        <v-card>
          <v-card-title class="text-h5 text-center"> 로그인 </v-card-title>
          <v-card-text>
            <v-form>
              <v-text-field label="email" v-model="email"> </v-text-field>
              <v-text-field label="패스워드" v-model="password" type="password">
              </v-text-field>
              <v-btn color="primary" block @click="memberLogin">로그인</v-btn>
              <br />
              <v-row>
                <v-col
                  cols="6"
                  class="d-flex justify-center"
                  @click="googleLogin"
                  style="cursor: pointer"
                  >구글 로그인</v-col
                >
                <v-col
                  cols="6"
                  class="d-flex justify-center"
                  @click="kakakoLogin"
                  style="cursor: pointer"
                  >카카오 로그인</v-col
                >
              </v-row>
            </v-form>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    async memberLogin() {
      const loginData = {
        email: this.email,
        password: this.password,
      };

      const response = await axios.post(
        "http://localhost:8080/members/login",
        loginData
      );
      const token = response.data.token;
      localStorage.setItem("token", token);
      window.location.href = "/";
    },
    kakakoLogin() {
    },
    googleLogin() {},
  },
};
</script>
