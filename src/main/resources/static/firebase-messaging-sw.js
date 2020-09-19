console.log("service worker");
importScripts('https://www.gstatic.com/firebasejs/7.21.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.21.0/firebase-messaging.js');

var firebaseConfig = {
  apiKey: "AIzaSyB7m_sWQthaJzMa_jU1ZeXLnZ2xfDoo1dc",
  authDomain: "valid-amplifier-289205.firebaseapp.com",
  databaseURL: "https://valid-amplifier-289205.firebaseio.com",
  projectId: "valid-amplifier-289205",
  storageBucket: "valid-amplifier-289205.appspot.com",
  messagingSenderId: "79451104882",
  appId: "1:79451104882:web:0bac03503890db835e44eb"
};
firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();