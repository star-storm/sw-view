// JavaScript Document
function showCard(cardid,infoid,clsName,callback)
	{
	 var cardList = cardid.parentNode.getElementsByTagName("span");
	  for(i=0;i<cardList.length;i++){
	   if(cardid == cardList[i]) {
			cardList[i].className = clsName + "On";
			eval("document.getElementById('"+infoid + i + "').style.display=\"inline\";");
			callback(cardid.id);
	   }
	   if(cardid != cardList[i]){
			cardList[i].className = clsName + "Off";
			eval("document.getElementById('"+infoid + i + "').style.display=\"none\";"); 
		}
		
	   }
		$(".scrollDiv").niceScroll({
				 cursorcolor:"#446ba9",
				 touchbehavior: true,
				 cursoropacitymax: 1,
				 cursorborder: "0",
				 cursorwidth: "10px",
				 autohidemode: true}).resize(); 
	}

function showCardMore(cardid,infoid,moreid,clsName)
	{
	 var cardList = cardid.parentNode.getElementsByTagName("span");
	  for(i=0;i<cardList.length;i++){
	   if(cardid == cardList[i]) {
			cardList[i].className = clsName + "On";
			eval("document.getElementById('"+infoid + i + "').style.display=\"inline\";"); 
			eval("document.getElementById('"+moreid + i + "').style.display=\"inline\";"); 
	   }
	   if(cardid != cardList[i]){
			cardList[i].className = clsName + "Off";
			eval("document.getElementById('"+infoid + i + "').style.display=\"none\";"); 
			eval("document.getElementById('"+moreid + i + "').style.display=\"none\";");
		}
		
	   }
		
	}

function showCardMore0(cardid,infoid,moreid,clsName,callback)
	{
	 var cardList = cardid.parentNode.getElementsByTagName("span");
	  for(i=0;i<cardList.length;i++){
	   if(cardid == cardList[i]) {
			cardList[i].className = clsName + "On";
			eval("document.getElementById('"+infoid + i + "').style.display=\"inline\";"); 
			eval("document.getElementById('"+moreid + i + "').style.display=\"inline\";"); 
			callback(cardid.id);
	   }
	   if(cardid != cardList[i]){
			cardList[i].className = clsName + "Off";
			eval("document.getElementById('"+infoid + i + "').style.display=\"none\";"); 
			eval("document.getElementById('"+moreid + i + "').style.display=\"none\";");
		}
		
	   }
		
	}
function refreshScroll() {
	$('.scrollDiv').getNiceScroll().resize();
	$(".scrollDiv").niceScroll({
		 cursorcolor:"#446ba9",
		 touchbehavior: true,
		 cursoropacitymax: 1,
		 cursorborder: "0",
		 cursorwidth: "10px",
		 autohidemode: true}).resize(); 
}