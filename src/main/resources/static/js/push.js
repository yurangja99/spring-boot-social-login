function customShowNotification () {
    var img = '/images/icon.png';
    var title = 'Push Notification';
    var text = 'Push Message';
    if ('Notification' in window) {
        Notification.requestPermission();
        if (Notification.permission === 'granted') {
            var notification = new Notification(title, {body: text, icon: img});
        } else {
            alert(title + '\n' + text);
        }
    } else {
        alert(title + '\n' + text);
    }
}