"use strict";(function(a){"use strict";// isAddMaxFile = window.isAddMaxFile;
function b(b,c){this.element=a(b),this.options=a.extend({},a.fn["ananas-validations"].defaults,this.element.data(),c),this.init()}b.prototype={init:function init(){var b=this.element;b.click(function(b){a("#pickSize").val()&&a("#pickQuantity").val()?a(".empty-error").css("display","none"):(b.preventDefault(),a(".empty-error").css("display","block"))})},destroy:function destroy(){a.removeData(this.element[0],"ananas-validations")}},a.fn["ananas-validations"]=function(c,d){return this.each(function(){var e=a.data(this,"ananas-validations");e?e[c]&&e[c](d):a.data(this,"ananas-validations",new b(this,c))})},a.fn["ananas-validations"].defaults={fileExtension:!1,holdMessageError:""},a(function(){a("[data-ananas-validations]")["ananas-validations"]()})})(jQuery,window);