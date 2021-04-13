/* global jQuery, Coral */
(function($, Coral) {
    "use strict";

    console.log(" --------CLIENTLIBS LOADED------- ");

    var registry = $(window).adaptTo("foundation-registry");

    // Validator for required for multifield max and min items
    registry.register("foundation.validation.validator", {
        selector: "[data-validation=training-validation]",
        validate: function(element) {
            var el = $(element);
            let max=el.data("max-items");
            let min=el.data("min-items");
            let items=el.children("coral-multifield-item").length;
            let domitems=el.children("coral-multifield-item");
            console.log("{} : {} :{} ",max,min,items);
            if(items>max){
              /* Use below line if you don't want to add item in multifield more than max limit */
              //domitems.last().remove();
              return "You can add maximum "+max+" books. You are trying to add "+items+"th book."
            }
            if(items<min){
                return "You add minimum "+min+" books."
            }
        }
    });

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=first-name]",
        validate: function(element) {
            let el = $(element);
            let pattern=/[^a-zA-Z ]/;
            let value=el.val();
            if(pattern.test(value)){
               return "Special characters or number is not allowed.";
            }

        }
    });

     registry.register("foundation.validation.validator", {
        selector: "[data-validation=last-name]",
        validate: function(element) {
            let el = $(element);
            let pattern=/[^a-zA-Z0-9 ]/;
            let value=el.val();
            if(pattern.test(value)){
               return "Special characters are not allowed";
            }

        }
    });

   
})(jQuery, Coral);
