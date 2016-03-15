# Introduction #
Applications are in fact just [components](Implemenation#Component.md), the suffix Application is merely added for semantic reasons.

# Details #

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages

# iTunes #
Connection to iTunes is established using [iTunes Controller](http://dot-totally.co.uk/software/itunescon/), which depends on [JACOB Java-COM Bridge](http://danadler.com/jacob/). Detecting that iTunes runs should be possible using [Native](Native.md), but this approach proved to be unreliable as no information on iTunes' COM state can be acquired. Releasing the COM connection requires the use of 'ComThread.Release()',  so possible side effects on other COM connections are expected. Listening to events is made optional through the constructor. When this option is enabled, iTunes is exited because of the absence of reliable ways to release 'DispatchEvents'. These COM issues can be investigated by enabling the **com.jacob.debug** system property. Testing with **com.jacob.autogc** is [suggested](http://stackoverflow.com/questions/980483/jacob-doesnt-release-the-objects-properly) to be a promising starting point.