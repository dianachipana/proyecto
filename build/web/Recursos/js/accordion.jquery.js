jQuery.fn.initMenu=function(){return this.each(function(){var e=$(this).get(0);$(".acitem",this).hide();$("li.expand > .acitem",this).show();$("li.expand > .acitem",this).prev().addClass("active");$("li a",this).click(function(e){e.stopImmediatePropagation();var t=$(this).next();var n=this.parentNode.parentNode;if($(n).hasClass("noaccordion")){if(t[0]===undefined){window.location.href=this.href}$(t).slideToggle("normal",function(){if($(this).is(":visible")){$(this).prev().addClass("active")}else{$(this).prev().removeClass("active")}});return false}else{if(t.hasClass("acitem")&&t.is(":visible")){if($(n).hasClass("collapsible")){$(".acitem:visible",n).first().slideUp("normal",function(){$(this).prev().removeClass("active")});return false}return false}if(t.hasClass("acitem")&&!t.is(":visible")){$(".acitem:visible",n).first().slideUp("normal",function(){$(this).prev().removeClass("active")});t.slideDown("normal",function(){$(this).prev().addClass("active")});return false}}})})}