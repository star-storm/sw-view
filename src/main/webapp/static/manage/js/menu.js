

$(function(){
    $(".menuUl > li > a").click(function(){
	     $(this).addClass("menuOn").parents().siblings().find("a").removeClass("menuOn");
		 $(this).parents().siblings().find(".menuEj").hide(300);
		 $(this).siblings(".menuEj").toggle(300);
		 $(this).parents().siblings().find(".menuEj > li > .thr").hide().parents().siblings().find(".thr_nr").hide();
		
	})
	
    $(".menuEj > li > a").click(function(){
        $(this).addClass("sen_x").parents().siblings().find("a").removeClass("sen_x");
        $(this).parents().siblings().find(".thr").hide(300);	
	    $(this).siblings(".thr").toggle(300);	
	})

    $(".thr > li > a").click(function(){
	     $(this).addClass("xuan").parents().siblings().find("a").removeClass("xuan");
		 $(this).parents().siblings().find(".thr_nr").hide();	
	     $(this).siblings(".thr_nr").toggle();
	})






})


 




























