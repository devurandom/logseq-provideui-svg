# Reproduce missing svg:use elements

1. Install Logseq 0.9.9 (I used the `com.logseq.Logseq` Flatpak at commit `824c0780b01922b75fa260f3835530aa9a7bbe2b2e36e9c1414f9cb0aab409ce`)
2. `pnpm watch` to build this plugin
3. Follow https://github.com/logseq/logseq-plugin-samples#usage to load the plugin into Logseq:
   1. Navigate to Three Dots > Settings > Advanced
   2. Enable "Developer mode"
   3. Navigate to Three Dots > Plugins
   4. Click "Load unpacked plugin"
   5. Select the root of this plugin (where the `packages.json` is)
4. Open Developer Tools by pressing Ctrl+Alt+I (on Linux)
5. On any page (I used the "Journals" page) enter `{{renderer some text}}`
6. Move the caret / cursor to another line so that the text you just entered is rendered
7. In the DOM displayed in the Developer Tools, navigate to the inserted SVG node
8. Observe that under `<defs>` you find the `<g id="Tree">` (upper case T) node with its content, but the `<g id="tree">` (lower case T) with the expected `<use>` element is missing

# Compare with regular browser

1. `pnpm watch` to build this plugin and start the internal web server
2. Open a browser (I used Firefox 114)
3. Navigate to http://localhost:8080 (or whatever `pnpm` displayed after "HTTP server available at")
4. Open Developer Tools (often by right-clicking the page and using the "Inspect" option)
5. In the DOM displayed in the Developer Tools, navigate to the inserted SVG node inside the `<div id="app">` inside the `<body>` of the document
6. Observe that under `<defs>` you find the `<g id="tree">` with the `<use>` element as expected
