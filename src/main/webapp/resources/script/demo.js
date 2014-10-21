
$(document).ready(function() {		
		//$('#left-panel').addClass('animated bounceInRight');
		$('#project-progress').css('width', '50%');
		$('#msgs-badge').addClass('animated bounceIn');	
		
		$('#my-task-list').popover({
			html:true			
		});
		
		$('#amountReceivedMoney').on('blur', function(){
			var totalPrice = $('#totalPriceMoney').text();
			var amountReceived = $('#amountReceivedMoney').val();

			var returnValue = 0;
			if(amountReceived > totalPrice){
				returnValue = amountReceived - totalPrice;
				$('#returnValueMoney').text(returnValue);
			}	
			else{
				$('#returnMoneyBox').text("Valor recebido é menor que o total!");
			}
			
		});
});