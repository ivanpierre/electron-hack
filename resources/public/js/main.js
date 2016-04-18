/*
	minimal start version
*/

var electron 	= require('electron');

// Report crashes to atom-shell.
require('crash-reporter').start();

// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the javascript object is GCed.
var mainWindow = null;

// NOTE: not all of the browserWindow options listed on the docs page work
// on all operating systems
const browserWindowOptions = {
  height: 850,
  title: 'hack',
  width: 1400,
  icon: __dirname + '/img/logo_96x96.png'
};

// This method will be called when atom-shell has done everything
// initialization and ready for creating browser windows.
electron.app.on('ready', function() {
  // Create the browser window.
  mainWindow = new electron.BrowserWindow(browserWindowOptions);

  // and load the index.html of the app.
  mainWindow.loadURL('file://' + __dirname + '/index.html');
});
