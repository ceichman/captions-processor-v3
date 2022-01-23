# captions-processor
Author: Charlie Eichman, 2022 <hr>
This project was created for preliminary processing of .srt-formatted video caption files as output from a speech-to-text database (such as Knowmia or Zoom).

# Functionality

# Usage
Methods are intended to be used on a file in the order they are defined in the source code in order to provide the cleanest output. This order is as follows:
<ul>
<li>fileToList()
<li>listToCaptions()
<li>removeEmptyCaptions()
<li>searchAndReplace();
<li>removeMultipleSpaces(captions);
<li>trimTrailingSpaces(captions);
<li>capitalizeFirstLetters(captions);
</ul> <br>
