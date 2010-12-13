/**
* Stripes jQuery Validation Plugin
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
* Usage:
*    <head>
*      ...
*      <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
*      <script type="text/javascript" src="/javascript/jquery.validation.js"></script>
*      <script type="text/javascript" src="/javascript/stripes.jquery.validation.js"></script>
*      ...
*    </head>
*    <body>
*      ...
*      <stripes:form ...>
*        ...
*        <stripes:field-metadata var="fieldMetadata">
*          $(function(){applyStripesValidation('${fieldMetadata.formId}', ${fieldMetadata});});
*        </stripes:field-metadata>
*      </stripes:form>
*      ...
*    </body>
*/

function applyStripesValidation(formId, fieldMetadata, userOptions)
{
	// get the form
	var form = $('#'+formId);
	
	var options = {invalidClass:'invalid', disableSubmit:true};
	
	// override default options with those that were passed in
	if (userOptions)
		$.extend(options, userOptions);
	
	// add validation info to the form
	form.validation(options);
   	
   	var property;
   	
   	// add validation for each field after translating field properties
	for (property in fieldMetadata)
	{
		// translate the field properties we got from Stripes
		// to corresponding jquery.validation.js properties
		var validationProperties = translate(fieldMetadata[property]);
		
		// apply the validation properties to the field
		var field = form.find('[name='+property+']').validation(validationProperties);

		if (fieldMetadata[property].type == 'Date')
			field.addClass('datefield');
	}

	// This function translates the field properties from stripes into the input for jquery.validation
	function translate(properties)
	{
		var translated = {};
		var property;
		
		for (property in properties)
		{
			switch (property)
			{
				case 'label':
				case 'required':
				case 'minlength':
				case 'maxlength':
					// these properties are the same so just copy
					translated[property]=properties[property];
					break;
				case 'minvalue': translated['min']=properties[property];break;
				case 'maxvalue': translated['max']=properties[property];break;
				case 'mask': translated['regex']=properties[property];break;
				case 'type':
					switch (properties[property].toLowerCase())
					{
						case 'short':
						case 'int':
						case 'integer':
						case 'long':
							translated['int']=true;
							break;
						case 'float':
						case 'double':
						case 'bigdecimal':
							translated['float']=true;
							break;
						case 'date':
							translated['date']='m/d/yy';
							break;
					}
					break;
				case 'typeConverter':
					switch (properties[property])
					{
						case 'CreditCardTypeConverter':translated['creditCard']=true;break;
						case 'EmailTypeConverter':translated['email']=true;break;
					}
					break;
			}
		}
		
		return translated;
	}
}

// add the date picker control to fields with a class of datepicker
$(function(){
	if (!$.datepicker)
		return;
	$.datepicker.setDefaults({showOn: 'both',
								dateFormat:'m/d/yy',
								buttonImageOnly: true,
								buttonImage: contextPath+'/images/jquery/datepicker.calendar.gif',
								buttonText: 'Calendar'});

	$('.datefield').datepicker();
});