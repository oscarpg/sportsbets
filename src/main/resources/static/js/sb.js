
$(function() {

    
    /*
     * =======================================
     * NAMESPACE CREATION
     * =======================================
     */
    

    if (!$.com) {
        $.com = {};
    }
    if (!$.com.rurallabs) {
        $.com.rurallabs = {};
    }
    if ($.com.rurallabs.sportsbets) {
        throw new Error('com.rurallabs.sportsbets already exists!');
    }
  
    $.com.rurallabs.sportsbets = {};
  

    
    /*
     * =======================================
     * GLOBAL VARIABLES (sportsbets-object scope)
     * =======================================
     */
    
    
    $.com.rurallabs.sportsbets.scoreboard = {};
    $.com.rurallabs.sportsbets.scoreboard.POSITION_SELECTOR = '#scoreboard tbody tr td.scoreboard_position';    
    $.com.rurallabs.sportsbets.scoreboard.POSITION_CLASS_PREFIX = 'scoreboard_pos';
    
    $.com.rurallabs.sportsbets.lang = {};
    $.com.rurallabs.sportsbets.lang.prototypes = {};
    $.com.rurallabs.sportsbets.lang.LANG_INPUT_CLASS = 'langInput';
    $.com.rurallabs.sportsbets.lang.VALUE_INPUT_CLASS = 'valueInput';
    $.com.rurallabs.sportsbets.lang.LANG_BLOCK_CLASS_SUFFIX = 'lang-element';
    $.com.rurallabs.sportsbets.lang.LANG_BLOCK_NEW_CLASS_SUFFIX = '-lang-new';
    $.com.rurallabs.sportsbets.lang.LANG_BLOCK_EXISTING_CLASS_SUFFIX = '-lang-existing';
    $.com.rurallabs.sportsbets.lang.LANG_BLOCK_PROTOTYPE_ID_SUFFIX = '-lang-prototype';
        
    
    
    /*
     * =======================================
     * INITIALIZATION
     * =======================================
     */
    
    
    $.com.rurallabs.sportsbets.initialize =
        function() {
            
            var ncomplo = this;

            /*
             * --------------------------
             * Initialize scoreboard
             * --------------------------
             */
            
            this.scoreboard.removeRepeatedPositions();
            
            /*
             * --------------------------
             * Initialize lang prototypes
             * --------------------------
             */
        
            // Retrieves all lang-oriented prototype divs, which
            // "id" attribute should end with "-lang"
            var prototypes = $('[id$=' + ncomplo.lang.LANG_BLOCK_PROTOTYPE_ID_SUFFIX + ']');
            
            prototypes.each(
                    function() {
                        var prototype = this;
                        ncomplo.lang.prototypes[this.id] = 
                            {
                                name : 
                                    prototype.id.substring(0,this.id.indexOf(ncomplo.lang.LANG_BLOCK_PROTOTYPE_ID_SUFFIX)),
                                index : 
                                    (function() {
                                        var name = 
                                            prototype.id.substring(0,prototype.id.indexOf(ncomplo.lang.LANG_BLOCK_PROTOTYPE_ID_SUFFIX));
                                        var existingClass =
                                            name + ncomplo.lang.LANG_BLOCK_EXISTING_CLASS_SUFFIX;
                                        var existingElements = 
                                            $(prototype).parent().children().filter('.' + existingClass);
                                        existingElements.each(
                                                function() {
                                                    $(this).addClass(this.LANG_BLOCK_CLASS_SUFFIX);
                                                });
                                        return existingElements.size();
                                    })(),
                                element: $(this)
                            };
                        $(this).remove();
                    });

            
        };
        
        
        
    /*
     * =======================================
     * LANG
     * =======================================
     */
        
        
    $.com.rurallabs.sportsbets.scoreboard.removeRepeatedPositions =
        function(langElementId) {
        
            var scoreboard = this;
            
            var scoreboardEntries = $(scoreboard.POSITION_SELECTOR);
            
            if (scoreboardEntries != null) {
            
                (function () {
                    var lastPos = -1;
                    scoreboardEntries.each(
                        function() {
                            var posElement = $(this);
                            var posSpan = posElement.find('span');
                            var pos = parseInt(posSpan.html());
                            if (lastPos != pos) {
                                lastPos = pos;
                            } else {
                                posSpan.html('');
                            }
                            posElement.parent().addClass(scoreboard.POSITION_CLASS_PREFIX + pos);
                        });
                })();
                
            }
            
        };
        
            
        
        
        
    /*
     * =======================================
     * LANG
     * =======================================
     */
        
        
    $.com.rurallabs.sportsbets.lang.add =
        function(langElementId) {
        
            var defaultLangElement = $('#'+langElementId);
            var prototypeObject = this.prototypes[langElementId + this.LANG_BLOCK_PROTOTYPE_ID_SUFFIX];
            
            var newLangElement = prototypeObject.element.clone();
            newLangElement.attr('id',null);
            newLangElement.addClass(prototypeObject.name + this.LANG_BLOCK_NEW_CLASS_SUFFIX);
            newLangElement.addClass(this.LANG_BLOCK_CLASS_SUFFIX);
            
            var langInput = newLangElement.children().filter('.' + this.LANG_INPUT_CLASS);
            var valueInput = newLangElement.children().filter('.' + this.VALUE_INPUT_CLASS);

            langInput.attr(
                    'name',
                    langInput.attr('name').replace('$index$',prototypeObject.index));
            valueInput.attr(
                    'name',
                    valueInput.attr('name').replace('$index$',prototypeObject.index));

            langInput.attr(
                    'id',
                    langInput.attr('id').replace('$index$',prototypeObject.index));
            valueInput.attr(
                    'id',
                    valueInput.attr('id').replace('$index$',prototypeObject.index));
            
            prototypeObject.index++;

            
            var existingElements =
                defaultLangElement.parent().children().filter('.' + prototypeObject.name + this.LANG_BLOCK_EXISTING_CLASS_SUFFIX);
            var newElements =
                defaultLangElement.parent().children().filter('.' + prototypeObject.name + this.LANG_BLOCK_NEW_CLASS_SUFFIX);

            if (newElements.size() > 0) {
                newElements.last().after(newLangElement);
            } else if (existingElements.size() > 0) {
                existingElements.last().after(newLangElement);
            } else {
                defaultLangElement.after(newLangElement);
            }
            
        };
        
        
        
        $.com.rurallabs.sportsbets.lang.remove =
            function(removalLinkElement) {
                var langParent = $(removalLinkElement);
                while (!langParent.hasClass(this.LANG_BLOCK_CLASS_SUFFIX)) {
                    langParent = langParent.parent();
                }
            
                langParent.remove();
            };
            
            
            
            
    /*
     * =======================================
     * INITIALIZATION
     * =======================================
     */
        
    $.com.rurallabs.sportsbets.initialize();
    
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].onclick = function(){
            /* Toggle between adding and removing the "active" class,
            to highlight the button that controls the panel */
            this.classList.toggle("active");

            /* Toggle between hiding and showing the active panel */
            var panel = this.nextElementSibling;
            if (panel.style.display === "block") {
                panel.style.display = "none";
            } else {
                panel.style.display = "block";
            }
            return false;
        }
    }
});


