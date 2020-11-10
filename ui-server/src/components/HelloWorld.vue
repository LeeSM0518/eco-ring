<template>
  <v-container fluid class="px-0 py-0">
    <v-carousel
      cycle
      hide-delimiter-background
      hide-delimiters
      show-arrows-on-hover
    >
      <v-carousel-item style="background-color: #90BCD3;">
        <v-sheet style="background-color: #90BCD3;">
          <v-row class="ma-0">
            <v-row class="ma-3">
              <v-img
                max-height="50"
                max-width="50"
                src="../assets/img/humidity/weather.png"
              ></v-img>
              <p style="font-size: xx-large" class="ml-3 font">
                {{ date }}
              </p>
            </v-row>
          </v-row>
          <v-row class="ma-0 mt-3">
            <v-col>
              <v-img
                style="margin-left: 80px;"
                contain
                max-width="200"
                src="../assets/img/humidity/waterdrop.png"
              />
            </v-col>
            <v-col class="ml-n15">
              <div
                class="ml-n10 font"
                style="font-size: 170px; font-weight: 300"
              >
                {{ humidity }}
                <span style="font-size: 50px; font-weight: 200">
                  %
                </span>
              </div>
              <v-row class="mt-n15">
                <v-img
                  contain
                  max-width="30"
                  src="../assets/img/humidity/address.png"
                />
                <p
                  class="font"
                  style="margin-top: 20px; margin-left: 10px; font-size:20px;"
                >
                  대전 유성구 덕명동 학하서로 121번길
                </p>
              </v-row>
            </v-col>
          </v-row>
        </v-sheet>
      </v-carousel-item>
      <v-carousel-item>
        <v-sheet :class="dustState.id" height="100%">
          <v-row class="ma-0">
            <v-row class="ma-3">
              <v-img
                max-height="50"
                max-width="50"
                src="../assets/img/humidity/weather.png"
              ></v-img>
              <p style="font-size: xx-large" class="ml-3 font">
                {{ date }}
              </p>
            </v-row>
          </v-row>
          <v-row justify="center" class="mt-n10">
            <v-col>
              <v-row justify="center">
                <p
                  class="font"
                  :style="`font-size: xxx-large; color: ${dustState.textColor}`"
                >
                  {{ dustState.text }}
                </p>
              </v-row>
              <v-row justify="center" class="mt-n8">
                <v-img
                  id="dustImage"
                  contain
                  max-width="250"
                  :src="getImage(dustState.id)"
                />
              </v-row>
            </v-col>
          </v-row>
        </v-sheet>
      </v-carousel-item>
      <v-carousel-item class="temperature">
        <v-row class="ma-0">
          <v-row class="ma-3">
            <v-img
              max-height="50"
              max-width="50"
              src="../assets/img/humidity/weather.png"
            ></v-img>
            <p style="font-size: xx-large" class="ml-3 font">
              {{ date }}
            </p>
          </v-row>
          <v-row justify="center" class="text-center mt-n7">
            <v-col cols="12">
              <div class="font" style="font-size: 170px; font-weight: 300">
                {{ temperature }}
                <span style="font-size: 70px; font-weight: 400;">
                  ℃
                </span>
              </div>
              <v-row class="mt-n10 ml-n15" justify="center">
                <v-img
                  contain
                  max-width="30"
                  src="../assets/img/humidity/address.png"
                />
                <p
                  class="font"
                  style="margin-top: 20px; margin-left: 10px; font-size:30px;"
                >
                  대전 유성구 덕명동 학하서로 121번길
                </p>
              </v-row>
            </v-col>
          </v-row>
        </v-row>
      </v-carousel-item>
    </v-carousel>
  </v-container>
</template>

<script>
let interval = null;

export default {
  name: 'HelloWorld',

  data: () => ({
    date: '',

    veryBad: {
      text: '미세먼지 매우나쁨',
      textColor: '#d02126',
      id: 'verybad',
    },
    bad: {
      text: '미세먼지 나쁨',
      textColor: '#f39800',
      id: 'bad',
    },
    good: {
      text: '미세먼지 좋음',
      textColor: '#4ab135',
      id: 'good',
    },
    veryGood: {
      text: '미세먼지 매우좋음',
      textColor: '#036eb8',
      id: 'verygood',
    },

    humidity: 0,
    temperature: 0,

    dustState: {
      text: '미세먼지 매우나쁨',
      textColor: '#d02126',
      id: 'verybad',
    },
    image: '',
  }),

  methods: {
    currentDate() {
      let today = new Date();
      this.date = today.toLocaleString();
    },
    get() {
      return $.ajax({
        url: 'http://192.168.10.204:4000/data',
        type: 'GET',
        dataType: 'json',
      });
    },

    async getData() {
      const { data } = await this.get();
      this.currentDate();
      this.humidity = data.humidity;
      this.temperature = data.temperature;
      switch (data.status) {
        case '매우 나쁨':
          this.dustState = this.veryBad;
          break;
        case '나쁨':
          this.dustState = this.bad;
          break;
        case '보통':
          this.dustState = this.good;
          break;
        default:
          this.dustState = this.veryGood;
          break;
      }
    },
    getImage(name) {
      var images = require.context('../assets/img/main/', false, /\.png$/);
      return images(`./${name}.png`);
    },
  },

  created() {
    this.getData();
    interval = setInterval(this.getData, 1000);
  },

  destroyed() {
    clearInterval(interval);
  },
};
</script>

<style scoped>
@font-face {
  font-family: 'BMJUA';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}

.font {
  font-family: 'BMJUA';
}

.temperature {
  background-image: url('../assets/img/temperature/background.png');
  background-size: 100% 100%;
}

.verygood {
  background-image: url('../assets/img/main/verygoodwave.png');
  background-size: 100% 100%;
}

.good {
  background-image: url('../assets/img/main/goodwave.png');
  background-size: 100% 100%;
}

.bad {
  background-image: url('../assets/img/main/badwave.png');
  background-size: 100% 100%;
}

.verybad {
  background-image: url('../assets/img/main/verybadwave.png');
  background-size: 100% 100%;
}
</style>
