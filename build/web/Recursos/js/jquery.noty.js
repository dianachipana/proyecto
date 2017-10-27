function noty(e){return jQuery.noty(e)}(function(e){e.noty=function(t,n){var r=this;var i=null;var s=false;r.init=function(t){r.options=e.extend({},e.noty.defaultOptions,t);r.options.type=r.options.cssPrefix+r.options.type;r.options.id=r.options.type+"_"+(new Date).getTime();r.options.layout=r.options.cssPrefix+"layout_"+r.options.layout;if(r.options.custom.container)n=r.options.custom.container;s=e.type(n)==="object"?true:false;return r.addQueue()};r.addQueue=function(){var t=e.inArray(r.options.layout,e.noty.growls)==-1?false:true;if(!t)r.options.force?e.noty.queue.unshift({options:r.options}):e.noty.queue.push({options:r.options});return r.render(t)};r.render=function(t){var o=s?n.addClass(r.options.theme+" "+r.options.layout+" noty_custom_container"):e("body");if(t){if(e("ul.noty_cont."+r.options.layout).length==0)o.prepend(e("<ul/>").addClass("noty_cont "+r.options.layout));o=e("ul.noty_cont."+r.options.layout)}else{if(e.noty.available){var u=e.noty.queue.shift();if(e.type(u)==="object"){e.noty.available=false;r.options=u.options}else{e.noty.available=true;return r.options.id}}else{return r.options.id}}r.container=o;r.bar=e('<div class="noty_bar"/>').attr("id",r.options.id).addClass(r.options.theme+" "+r.options.layout+" "+r.options.type);i=r.bar;i.append(r.options.template).find(".noty_text").html(r.options.text);i.data("noty_options",r.options);r.options.closeButton?i.addClass("noty_closable").find(".noty_close").show():i.find(".noty_close").remove();i.find(".noty_close").bind("click",function(){i.trigger("noty.close")});if(r.options.buttons)r.options.closeOnSelfClick=r.options.closeOnSelfOver=false;if(r.options.closeOnSelfClick)i.bind("click",function(){i.trigger("noty.close")}).css("cursor","pointer");if(r.options.closeOnSelfOver)i.bind("mouseover",function(){i.trigger("noty.close")}).css("cursor","pointer");if(r.options.buttons){$buttons=e("<div/>").addClass("noty_buttons");i.find(".noty_message").append($buttons);e.each(r.options.buttons,function(t,n){bclass=n.type?n.type:"gray";$button=e("<button/>").addClass(bclass).html(n.text).appendTo(i.find(".noty_buttons")).bind("click",function(){if(e.isFunction(n.click)){n.click.call($button,i)}})})}return r.show(t)};r.show=function(t){if(r.options.modal)e("<div/>").addClass("noty_modal").addClass(r.options.theme).prependTo(e("body")).fadeIn("fast");i.close=function(){return this.trigger("noty.close")};t?r.container.prepend(e("<li/>").append(i)):r.container.prepend(i);if(r.options.layout=="noty_layout_topCenter"||r.options.layout=="noty_layout_center"){e.noty.reCenter(i)}i.bind("noty.setText",function(t,n){i.find(".noty_text").html(n);e.noty.reCenter(i)});i.bind("noty.getId",function(e){return i.data("noty_options").id});i.one("noty.close",function(t){var n=i.data("noty_options");if(n.modal)e(".noty_modal").fadeOut("fast",function(){e(this).remove()});i.clearQueue().stop().animate(i.data("noty_options").animateClose,i.data("noty_options").speed,i.data("noty_options").easing,i.data("noty_options").onClose).promise().done(function(){if(e.inArray(i.data("noty_options").layout,e.noty.growls)>-1){i.parent().remove()}else{i.remove();e.noty.available=true;r.render(false)}})});i.animate(r.options.animateOpen,r.options.speed,r.options.easing,r.options.onShow);if(r.options.timeout)i.delay(r.options.timeout).promise().done(function(){i.trigger("noty.close")});return r.options.id};return r.init(t)};e.noty.get=function(t){return e("#"+t)};e.noty.close=function(t){e.noty.get(t).trigger("noty.close")};e.noty.setText=function(t,n){e.noty.get(t).trigger("noty.setText",n)};e.noty.closeAll=function(){e.noty.clearQueue();e(".noty_bar").trigger("noty.close")};e.noty.reCenter=function(t){t.css({left:(e(window).width()-t.outerWidth())/2+"px"})};e.noty.clearQueue=function(){e.noty.queue=[]};e.noty.queue=[];e.noty.growls=["noty_layout_topLeft","noty_layout_topRight","noty_layout_bottomLeft","noty_layout_bottomRight"];e.noty.available=true;e.noty.defaultOptions={layout:"top",theme:"noty_theme_default",animateOpen:{height:"toggle"},animateClose:{height:"toggle"},easing:"swing",text:"",type:"alert",speed:500,timeout:5e3,closeButton:false,closeOnSelfClick:true,closeOnSelfOver:false,force:false,onShow:false,onClose:false,buttons:false,modal:false,template:'<div class="noty_message"><span class="noty_text"></span><div class="noty_close"></div></div>',cssPrefix:"noty_",custom:{container:null}};e.fn.noty=function(t){return this.each(function(){new e.noty(t,e(this))})}})(jQuery)