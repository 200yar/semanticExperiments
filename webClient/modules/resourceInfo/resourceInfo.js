// Generated by CoffeeScript 1.6.3
(function() {
  var controller, model, view, _base;

  controller = function(sb) {
    var destroy, init, model, parseRDF, rdfNS, rdfsNS, search, view;
    model = null;
    view = null;
    rdfsNS = "http://www.w3.org/2000/01/rdf-schema#";
    rdfNS = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    init = function() {
      model = sb.getModel("model");
      sb.mixin(model, sb.observable);
      model.subscribe(this);
      view = new sb.getView("view")(sb, model);
      view.init();
      sb.subscribe("simpleQuery/select", search);
      return search('');
    };
    search = function(resource) {
      var sparql;
      if (resource.trim !== '') {
        sparql = "CONSTRUCT { <" + resource + "> ?p ?o   }\nWHERE     { <" + resource + "> ?p ?o . }";
        return $.ajax({
          url: "sparql?" + $.param({
            query: sparql
          }),
          dataType: "text",
          success: function(res) {
            model.results = parseRDF(res);
            return model.notify();
          }
        });
      }
    };
    parseRDF = function(res) {
      var i, rdf, triple, triples, _results;
      rdf = $.rdf().load(res, {});
      triples = rdf.databank.tripleStore;
      sb.debug(triples);
      _results = [];
      for (i in triples) {
        triple = triples[i];
        _results.push({
          property: triple.property.value,
          object: triple.object.value
        });
      }
      return _results;
    };
    destroy = function() {};
    return {
      init: init,
      destroy: destroy
    };
  };

  model = {
    rdf: void 0,
    results: []
  };

  view = function(sb, model) {
    var c, init, result, select, tmpl, update;
    result = null;
    c = null;
    tmpl = null;
    update = function(ev) {
      c.empty();
      return sb.tmpl(tmpl, {
        results: model.results
      }).appendTo(c);
    };
    select = function(ev) {
      return sb.publish("simpleQuery/select", $(this).attr('rel'));
    };
    init = function() {
      model.subscribe(this);
      tmpl = sb.getTemplate("result");
      c = sb.getContainer();
      return c.delegate("td a", "click", select);
    };
    return {
      init: init,
      update: update
    };
  };

  if ((_base = swe.modules).resourceInfo == null) {
    _base.resourceInfo = {
      controller: controller,
      model: model,
      view: view
    };
  }

}).call(this);
