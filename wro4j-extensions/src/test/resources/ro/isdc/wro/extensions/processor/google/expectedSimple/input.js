(function(e){e.fn.hoverIntent=function(l,m){var d={sensitivity:7,interval:100,timeout:0},d=e.extend(d,m?{over:l,out:m}:l),f,h,i,j,k=function(c){f=c.pageX;h=c.pageY},n=function(c,a){a.hoverIntent_t=clearTimeout(a.hoverIntent_t);if(Math.abs(i-f)+Math.abs(j-h)<d.sensitivity)return e(a).unbind("mousemove",k),a.hoverIntent_s=1,d.over.apply(a,[c]);else i=f,j=h,a.hoverIntent_t=setTimeout(function(){n(c,a)},d.interval)},o=function(c){for(var a=(c.type=="mouseover"?c.fromElement:c.toElement)||c.relatedTarget;a&&
a!=this;)try{a=a.parentNode}catch(f){a=this}if(a==this)return false;var g=jQuery.extend({},c),b=this;if(b.hoverIntent_t)b.hoverIntent_t=clearTimeout(b.hoverIntent_t);if(c.type=="mouseover"){if(i=g.pageX,j=g.pageY,e(b).bind("mousemove",k),b.hoverIntent_s!=1)b.hoverIntent_t=setTimeout(function(){n(g,b)},d.interval)}else if(e(b).unbind("mousemove",k),b.hoverIntent_s==1)b.hoverIntent_t=setTimeout(function(){b.hoverIntent_t=clearTimeout(b.hoverIntent_t);b.hoverIntent_s=0;d.out.apply(b,[g])},d.timeout)};
return this.mouseover(o).mouseout(o)}})(jQuery);