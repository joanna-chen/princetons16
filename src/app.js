/**
 * Welcome to Pebble.js!
 *
 * This is where you write your app.
 */

var UI = require('ui');
// var ajax = require('ajax');
// var Vector2 = require('vector2');
// var dict = {'AppKeyContactName':};

// var parseFeed = function(data, quantity) {
//   var items = [];
//   for(var i = 0; i < quantity; i++) {
//     // Always upper case the description string
//     var title = data.list[i].weather[0].main;
//     title = title.charAt(0).toUpperCase() + title.substring(1);

//     // Get date/time substring
//     var time = data.list[i].dt_txt;
//     time = time.substring(time.indexOf('-') + 1, time.indexOf(':') + 3);

//     // Add to menu items array
//     items.push({
//       title:title,
//       subtitle:time
//     });
//   }

//   // Finally return whole array
//   return items;
// };

var main = new UI.Card({
  title: 'FIN',
//   icon: 'images/logo.png',
  subtitle: 'Time of your life!',
  body: 'Scroll down for tasks.',
  subtitleColor: 'indigo', // Named colors
  bodyColor: '#9a0036' // Hex colors
});

main.show();

main.on('click', 'down', function(e) {
  var card = new UI.Card();
  var pressed = 'false'; 
  card.title('School'); //type of activity
  card.show();
  card.on('click', 'select', function(e) {
    var menu = new UI.Menu({
      backgroundColor: 'green',
      sections: [{
        items: [{
          title: 'Commute', // need indication of started and stop
//           icon: 'bus',
          subtitle: '30:00'
        }, {
          title: 'Calculus', // need indication of started and stop
//           icon: 'math',
          subtitle: '45:00'
        }]
      }]   
    });
    menu.on('select', function(e) {
      if (pressed == 'false') {
        menu.backgroundColor = 'green' ;
        pressed = true;
        // send start and stop times 
      } else { 
        menu.backgroundColor = 'red';
        pressed = false;
      } 
    });
    menu.show();
  });
});
//////////////////
main.on('click', 'up', function(e) {
    var card2 = new UI.Card();
    card2.title('Health');
    card2.show();
    card2.on('click', 'select', function(e) {
    var menu = new UI.Menu({
      backgroundColor: 'red',
      sections: [{
        items: [{
          title: 'Gym', // need indication of started and stop
//           icon: 'weights',
          subtitle: '20:00'
        }, {
          title: 'Run', // need indication of started and stop
//           icon: 'shoes',
          subtitle: '25:00'
        }]
      }]   
    });
        ///////////////////
//     menu.on('select', function(e) {
//       if (!pressed) {
//         e.item.title.color(0xAAFFAA);
//         pressed = true;
//         // send start and stop times 
//       } else { 
//         e.item.title.color(0xFF5555);
//         pressed = false;
//       } 
//     });
    menu.show();
  });
});