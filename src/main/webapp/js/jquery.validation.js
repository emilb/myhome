/*
* jQuery Validation Plugin
*
* Copyright(c) 2007-2008 Aaron Porter http://www.mongus.com
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*
* Form Validation Options
*  valid: function to call when all fields are valid
*  invalid: function to call when not all fields are valid
*  submit: function to call immediately before submitting
*  invalidClass: string - class to add to fields when they are invalid (defaults to 'invalid')
*  fields: JSON containing name:options
*  notify: function(messages) - function to notify user of errors (defaults to an alert)
*  reset: function
*
* Field Validation Options
*  <type>: value
*  label: A user-friendly name for the field
*  error: String
*  valid: function
*  invalid: function
*
* Pre-defined Types:
*  'min': minimum numeric value
*  'max': maximum numeric value
*  'minLength': minimum number of characters
*  'maxLength': maximum number of characters
*  'required': required field
*  'numeric': positive whole number
*  'int': positive or negative whole number
*  'float': positive or negative floating point number
*  'email': valid email address 
*  'creditcard': valid credit card number (LUHN checked)
*  'regex': pattern to match against
*  'mask': pattern to match against as characters are typed
*  'confirm': value must match field specified
*  
*/

(function($){

$.fn.validation = function(originalOptions)
{
	originalOptions = $.extend({
						invalidClass: 'invalid',
						notify: function(messages){
							var message = 'Please correct the following problem' + (messages.length > 1 ? 's' : '') + ':\n' + messages.join('\n');
							alert(message);
						}}, originalOptions);
	
	function attachValidation(opts)
	{
		var options = opts || originalOptions;
		
		addCallback.call(this, 'valid', options.valid);
		addCallback.call(this, 'invalid', options.invalid);
		addCallback.call(this, 'submit', options.submit);
		
		if (options.label)
			this.setAttribute('label', options.label);

		if (this.tagName.toLowerCase() == 'form')
		{
			var form = this;

			if (!this['validation bound'])
			{			
				$(this).submit(validate).bind('reset', resetHandler);
				addCallback.call(this, 'reset', options.reset);
				this['validation bound'] = true;
			}
			
			if (options.disableSubmit)
			{
		   		addCallback.call(this, 'valid', function(){$(this).find(':submit').attr('disabled','');});
		   		addCallback.call(this, 'invalid', function(){$(this).find(':submit').attr('disabled','disabled');});
			}

			return $.each(options.fields || {}, function(name, value){
				$.each(form.elements, function(i, field){
					if (name!=field.name) return;
					attachValidation.call(field, value);
				});
			});
		}
		
		addCallback.call(this, 'invalid', originalOptions.invalidClass);
		
		this['validation message'] = options.error;
		var validationMethods = this['validation methods'] || (this['validation methods'] = []);
		
		var field = this;

		$.each(options, function(name, value){
			var method = $.fn.validation[name.toLowerCase()];
			
			if (method)
				validationMethods[validationMethods.length] = new method(field, value);
		});
		
		if (!this['validation bound'])
		{			
			$(this).blur(validate).change(validate).click(delayedValidate).keyup(validate).select(validate);
			this['validation bound'] = true;
		}
		
		if (!this.form['validation bound'])
			attachValidation.call(this.form, {});
		
		validate.call(this);
	}
	
	function resetHandler(e)
	{
		var form = this;

		setTimeout(function(){
			var i, c;
			for (i = 0, c = form.elements.length; i < c; i++)
				validate.call(form.elements[i]);

			callCallbacks.call(form, 'reset');
		}, 1);
	}
	
	function delayedValidate(e)
	{
		setTimeout(function(){validate.call(this,e);},100);
	}
	
	function isRadio(field)
	{
		return ((field.type || 'input').toLowerCase() == 'radio');
	}
	
	function validate(e)
	{
		// only run for the first radio button in a group
		if (isRadio(this) && (this.form.elements[this.name][0] != this))
			return validate.call(this.form.elements[this.name][0], e);
			
		var i, c, valid = true, invalidFields = [];
		
		// for fields
		var validationMethods = this['validation methods'];
		if (validationMethods)
		{
			var method;
			
			for (i = 0, c = validationMethods.length; i < c; i++)
			{
				method = validationMethods[i];
				
				if (method.validate)
					if (!method.validate.call(this, e))
						valid = false; 
			}
		}
		// for forms
		else if (this.elements)
		{
			var field;
			
			for (i = 0, c = this.elements.length; i < c; i++)
			{
				field = this.elements[i];
				
				if (field.disabled || (isRadio(field) && (this.elements[field.name][0] != field)))
					continue;
				
				if (field.isInvalid)
				{
					valid = false;
					
					invalidFields[invalidFields.length] = field;
				}
			}
		}
		
		var event = false;
		
		if (!valid && !this.isInvalid)
		{
			this.isInvalid = true;
			event = 'invalid';
		}
		else if ((typeof this.isInvalid == 'undefined') || (valid && this.isInvalid))
		{
			this.isInvalid = false;
			event = 'valid';
		}
		
		callCallbacks.call(this, event);
			
		if (this.form && event)
			validate.call(this.form);
		else if (!this.form && e) // if there is an event that was passed in we were called from onSubmit
		{
			if (valid)
			{
				callCallbacks.call(this, 'submit');
			}
			else
			{
				e.preventDefault();
				
				var messages = [];
				
				for (i = 0, c = invalidFields.length; i < c; i++)
					messages[messages.length] = getErrorMessage.call(invalidFields[i]);
					
				originalOptions.notify.call(this, messages, invalidFields);
				
				var firstField = invalidFields[0];
				
				if (typeof firstField.select == 'function') firstField.select();
				invalidFields[0].focus();
				
				if (typeof $.scrollTo == 'function')
					$.scrollTo(invalidFields[0]);
			}
		}
	}

	$.fn.validation.validate = validate;
	
	function getErrorMessage()
	{
		return	this['validation error'] ||
				this['validation message'] ||
				(getFieldName.call(this) + ' is invalid')
	}
	
	function callCallbacks(event)
	{
		var target = this;
		
		if (event)
			$.each(getCallbacks.call(this, event), function(i, callback){
				callback.call(target);
			});
	}
	
	function getFieldName()
	{
		var label = this.getAttribute('label');
		
		if (label)
			return label;

		label = $('label[for=' + this.id + ']');
		
		if (label.length)
			return label.text().replace(/:\s*$/,'');
		
		return this.name;
	}
	
	function getCallbacks(eventName)
	{
		var callbackArrays = this['validation callbacks'] || (this['validation callbacks'] = []);
		var callbacks = callbackArrays[eventName] || (callbackArrays[eventName] = []);
		return callbacks;
	}
	
	function addCallback(eventName, callback)
	{
		if (!callback) return;
		
		if (typeof callback == 'string')
		{
			addCallback.call(this, eventName, function(){$(this).addClass(callback);});
			var removeEvent = null;
			switch(eventName)
			{
				case 'valid': removeEvent = 'invalid'; break;
				case 'invalid': removeEvent = 'valid'; break;
			}
			if (removeEvent)
				addCallback.call(this, removeEvent, function(){$(this).removeClass(callback);});
				
			return;
		}
		
		var callbacks = getCallbacks.call(this, eventName);
		callbacks[callbacks.length] = callback;
	}
	
	return this.each(function(){
		attachValidation.call(this, originalOptions);
	});
}

$.fn.validation.addValidationClass = function(name, clazz)
{
	$.fn.validation[name.toLowerCase()] = clazz;
};

/******************************
* Minimum Value Validation
******************************/
$.fn.validation.addValidationClass('min', function(field, value){
	value = Number(value);
		
	this.validate = function() {
		return (this.value === '') || (Number(this.value) >= value);
	};
});

/******************************
* Maximum Value Validation
******************************/
$.fn.validation.addValidationClass('max', function(field, value){
	value = Number(value);
		
	this.validate = function() {
		return (this.value === '') || (Number(this.value) <= value);
	};
});

/******************************
* Minimum Length Validation
******************************/
$.fn.validation.addValidationClass('minLength', function(field, value){
	value = Number(value);
		
	this.validate = function() {
		return (this.value === '') || this.value.length >= value;
	};
});

/******************************
* Maximum Length Validation
******************************/
$.fn.validation.addValidationClass('maxLength', function(field, value){
	value = Number(value);
	field.maxLength = value;
		
	this.validate = function() {
		return (this.value === '') || this.value.length <= value;
	};
});

/******************************
* Required Validation
******************************/
$.fn.validation.addValidationClass('required', function(field, required){
	this.validate = function() {
		if (!required) return;
		
		switch (this.tagName.toLowerCase())
		{
			case 'input':
			case 'textarea':
				switch ((this.getAttribute('type') || 'text').toLowerCase())
				{
					case 'radio':
						var i, list = this.form.elements[this.name];
						
						for (i = 0; i < list.length; i++)
							if (list[i].checked)
								return true;
								
						return false;
					case 'text':
					case 'textarea':
					case 'hidden':
					case 'password':
						return this.value !== '';
					case 'checkbox':
						return this.checked;
				}
				break;
			case 'select':
				return $(this).val();
		}

		return false;
	};
});

/******************************
* RegEx Validation
******************************/
function regex(field, regex){
	if (typeof regex == 'string')
		regex = new RegExp(regex);
		
	this.validate = function() {
		return (this.value === '') || regex.test(this.value);
	};
};

$.fn.validation.addValidationClass('regex', regex);

/******************************
* Input Mask
******************************/
function mask(field, mask){
	if (typeof mask == 'string')
		mask = new RegExp('^'+mask+'$');

	$(field).keypress(function(e){
		e = $.event.fix(e || window.event);
		
		var c = e.charCode || ((e.type == "keypress") ? e.keyCode : 0);
			
		if ((c < 32) ||
			(35 <= c && c <= 40) ||
			(c == 46) ||
			(63232 <= c) ||
			e.altKey || e.ctrlKey || e.metaKey)
		{
			c = '';
		}
		else
			c = String.fromCharCode(c);

		normalizeSelectionIndices(e.target);
		
		var start = e.target.selectionStart;
		var end = e.target.selectionEnd;
		
		var value = e.target.value;
		
		var string = '';
		
		if (start > 0)
			string = value.substring(0, start);
			
		string += c;
		
		if (end < value.length)
			string += value.substring(end);
			
		if (!string.match(mask))
			e.preventDefault();
	});
	
	function normalizeSelectionIndices(element)
	{
		// If we weren't passed an element use document
		if (!element)
			element = document;

		// IE version
		if (element.createTextRange && !window.opera)
		{
			// Get the selected text range
			var selectedRange = document.selection.createRange();

			if (element.tagName == 'TEXTAREA')
			{
				// Get the complete text range for the element
				var range = selectedRange.duplicate();
				
				range.moveToElementText(element);
				range.setEndPoint('EndToEnd', range);
	
				// Set to the same variables as the ones built into Mozilla 
				element.selectionStart = range.text.length - selectedRange.text.length;
				element.selectionEnd = element.selectionStart + selectedRange.text.length;
			}
			else
			{
				// Get the complete text range for the element
				var startRange = element.createTextRange().duplicate();
				var endRange = element.createTextRange().duplicate();
	
				// Make sure the selected range is within the range for the element
				if ((startRange.compareEndPoints('StartToStart', selectedRange) == 1) ||
					(startRange.compareEndPoints('EndToEnd', selectedRange) == -1))
				{
					//mongus.error('Error: Caret appears to be outside the element!');
					return false;
				}
	
				// Place start and end at the beginning and end for the element
				var start = 0;
				var end = startRange.text.length;
	
				// Collapse the ranges to 0 width
				startRange.collapse(true);
				endRange.collapse(false);
	
				// Expand the end of the start range until we hit the selection's start
				while ((startRange.compareEndPoints('EndToStart', selectedRange) == -1) && (start < end))
				{
					startRange.moveEnd('character', 1);
					start++;
				}
	
				// Expand the start of the end range until we hit the selection's end
				while ((endRange.compareEndPoints('StartToEnd', selectedRange) == 1) && (start < end))
				{
					endRange.moveStart('character', -1);
					end--;
				}
	
				// Set to the same variables as the ones built into Mozilla 
				element.selectionStart = start;
				element.selectionEnd = end;
			}
		}

		return true;
	}
};
$.fn.validation.addValidationClass('mask', mask);

/******************************
* Numeric Mask and Validation
******************************/
$.fn.validation.addValidationClass('numeric', function(field){
	new mask(field, /^(\d+)?$/);
	return new regex(field, /^\d+$/);
});

/******************************
* Int Mask and Validation
******************************/
$.fn.validation.addValidationClass('int', function(field){
	new mask(field, /^[-]?(\d+)?$/);
	return new regex(field, /^[-]?\d+$/);
});

/******************************
* Float Mask and Validation
******************************/
$.fn.validation.addValidationClass('float', function(field){
	new mask(field, /^[-]?((\d+)|(\d*[.](\d+)?))?$/);
	return new regex(field, /^[-]?(\d+)|(\d*[.]\d+)$/);
});

/******************************
* Email Mask and Validation
******************************/
$.fn.validation.addValidationClass('email', function(field){
	new mask(field, /^([-a-zA-Z0-9_.]+(@(([-a-zA-Z0-9]+[.]?)+([a-zA-Z0-9]{2,4})?)?)?)?$/);
	return new regex(field, /^[-a-zA-Z0-9_.]+@([-a-zA-Z0-9]+[.])+([a-zA-Z0-9]{2,4})$/);
});

/******************************
* Date Mask and Validation
******************************/
$.fn.validation.addValidationClass('date', function(field, value){

	var m,d,y;
	
	value = value || 'mm/dd/yyyy';
	
	m = value.indexOf('m') + 100;
	d = value.indexOf('d') + 100;
	y = value.indexOf('y') + 100;
	
	if (m == 99 || d == 99 || y == 99)
	{
		alert('Invalid date format specified (' + value + ') for date validation.');
		return;
	}
	
	// There's got to be a better way to do this :P
	if (m == Math.min(m, Math.min(d, y))) m = 1;
	if (d == Math.min(m, Math.min(d, y))) d = 1;
	if (y == Math.min(m, Math.min(d, y))) y = 1;
	
	if (m == Math.max(m, Math.max(d, y))) m = 3;
	if (d == Math.max(m, Math.max(d, y))) d = 3;
	if (y == Math.max(m, Math.max(d, y))) y = 3;
	
	if (m != 1 && m != 3) m = 2;
	if (d != 1 && d != 3) d = 2;
	if (y != 1 && y != 3) y = 2;
	
	new mask(field, '^[' + value.replace(/[mdy]*/,'').replace(/-/g,'\\-') + '0-9]*$');
	
	var extractor = new RegExp(value.replace(/m+|d+|y+/g,'(\\d*)'));
	
	// This checks to see if the entered date is a real date
	this.validate = function()
	{
		if (this.value === '')
			return true;
			
		var matches = extractor.exec(this.value);
		if (!matches) return false;
		
		var month = matches[m], day = matches[d], year = matches[y];
		
		var date = new Date(year, month-1, day);
		
		return (date.getFullYear() == year) &&
				(date.getMonth() == month - 1) &&
				(date.getDate() == day);
	};
});

/******************************
* Credit Card Validation
******************************/
$.fn.validation.addValidationClass('creditcard', function(field, value){
	new mask(field, /^\d{0,16}$/);
	
	this.validate = function()
	{
		if (this.cardType)
			this.cardType = null;

		var number = this.value;
		
		if (number.length == 0)
			return true;
		
		if ((number.length > 0) && (number.length < 13 || number.length > 16))
			return false;

		var sum = 0, i, length, v;
		
		for (i = 0, length = number.length; i < length; i++)
		{
			v = Number(number.charAt(length - i - 1));

			if (i % 2 == 1)
				v *= 2;

			sum += Math.floor(v / 10) + v % 10;
		}
		var cardType = getCardType(number)

		if ((sum % 10 != 0) || !cardType)
			return false;
		
		this.cardType = cardType;
		
		return true;
	}
		
	function getCardType(number)
	{
		if (checkCard(number, 15, "34", "37"))
			return "AMEX";
		
		if (checkCard(number, 14, "30", "36", "38"))
			return "DinersClub";
			
		if (checkCard(number, 16, "6011"))
			return "Discover";
		
		if (checkCard(number, 15, "2014", "2149"))
			return "enRoute";
		
		if (checkCard(number, 16, "3088","3096","3112","3158","3337","3528"))
			return "JCB";
		
		if (checkCard(number, 16, "51", "52", "53", "54", "55"))
			return "MasterCard";
		
		if (checkCard(number, 13, "4") || checkCard(number, 16, "4"))
			return "VISA";

		return null;
	}
		
	function checkCard(number, length)
	{
		if (number.length != length)
			return false;
		
		var i, prefix;

		for (i = 2; i < arguments.length; i++)
		{
			prefix = arguments[i];
			if (number.substring(0, prefix.length) == prefix)
				return true;
		}
		
		return false;
	}
});

/******************************
* Confirmation Field Validation
******************************/
$.fn.validation.addValidationClass('confirm', function(field, confirmField){
	this.validate = function() {
		return this.value === this.form[confirmField].value;
	};
	
	function validateMe()
	{
		$.fn.validation.validate.call(field);
	}
	
	$(field.form[confirmField]).blur(validateMe).change(validateMe).keyup(validateMe);
});

})(jQuery);