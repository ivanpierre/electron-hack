Electron-Hack
=============

State :

-	compile : OK
-	running : OK

Hacking a little bit on Electron container with ClojureScript
-------------------------------------------------------------

This has been created with the [electron-template de ducky427](https://github.com/ducky427/electron-template) but is has been reworked...

The old [README.md](README.old.md).

Just to play a bit, and try to hack electron from inside electron, with interfacing enhancements as a goal.

As a sandbox it's not presumed to be working, but as I play with, it's probable to... ;)

Main entry point written in ClojureScript.

Uses standard electron app.

Warnings
--------

Only tested with no optimization with Figwheel... so to be seen with URLs changes.

The root for the main application is the root of the project. The root for the render application is the /app.

Bower and grunt files have not be touched, so they should not work as intended.

My usage
--------

The commands I use.

-	`lein clean` ; to clean most of the mess
-	`lein cljsbuild once` ; to compile the whole
-	`rlwarp lein figwheel` ; to launch figwheel for tests
-	`alias electron='/Applications/Electron.app/Contents/MacOS/Electron'` ; if you don't have an electron command when you install electron on your machine. In my case I'm on mac.
-	`electron app` ; to launch the application
