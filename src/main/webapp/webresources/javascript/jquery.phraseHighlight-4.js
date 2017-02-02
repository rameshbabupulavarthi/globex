/*

highlight v4

Highlights arbitrary terms.

<http://johannburkard.de/blog/programming/javascript/highlight-javascript-text-higlighting-jquery-plugin.html>

MIT license.

Johann Burkard
<http://johannburkard.de>
<mailto:jb@eaio.com>

*/

jQuery.fn.phraseHighlight = function(pat) {
 function innerHighlight(node, pat) {
  var patfmt = pat;
  var skip = 0;
  var excludetags = [];
  if (node.nodeType == 3) {
		// Check if the term is found
		var found = false;
		var str = node.data.toLowerCase();
		var middle;
		var begin;
		var end;
		var middleRegExp = '\\b' + patfmt.toLowerCase() + '\\b';
		var beginRegExp = '^'+patfmt.toLowerCase() + '\\b';
		var endRegExp = '\\b' + patfmt.toLowerCase()+'$';
		var ex;
		try 
		{
			middle = str.match(middleRegExp);
			if(!middle || middle.length == 0)
			{
				begin = str.match(beginRegExp);
				if(!begin || begin.length == 0)
				{
					end = str.match(endRegExp);	
					if(end)
					{
						ex = endRegExp;
					}
				}
				else
				{
					ex = beginRegExp;
				}
			}
			else
			{
				ex = middleRegExp;
			}
		}
		catch(e)
		{
			console.debug("ERROR " +  node);
		}				
		if (middle || begin || end) 
		{
			// Check for excluded tags
			if (jQuery.inArray($(node).parent().get(0).tagName,excludetags) > -1) 
			{
			}
			else 
			{  
				var ignorecase = true;
				// Case insensistive matching option
				if (ignorecase) {
					var pos = node.data.toLowerCase().regexIndexOf(ex);
				} else {
					var pos = node.data.regexIndexOf(ex);
				}
				
				// Create link element
				var spannode = document.createElement('span');
				spannode.className = 'phrasehighlight';				
				var middlebit = node.splitText(pos);						
				var endbit = middlebit.splitText(patfmt.length);
				var middleclone = middlebit.cloneNode(true);

				spannode.appendChild(middleclone);
				middlebit.parentNode.replaceChild(spannode, middlebit);
				skip = 1;
				//id += 1;
			}
		}	  
  }
  else if (node.nodeType == 1 && node.childNodes && !/(script|style)/i.test(node.tagName)) {
   for (var i = 0; i < node.childNodes.length; ++i) {
    i += innerHighlight(node.childNodes[i], pat);
   }
  }
  return skip;
 }
 return this.length && pat && pat.length ? this.each(function() {
  innerHighlight(this, pat.toUpperCase());
 }) : this;
};

jQuery.fn.removePhraseHighlight = function() {
 return this.find("span.phrasehighlight").each(function() {
  this.parentNode.firstChild.nodeName;
  with (this.parentNode) {
   replaceChild(this.firstChild, this);
   normalize();
  }
 }).end();
};
