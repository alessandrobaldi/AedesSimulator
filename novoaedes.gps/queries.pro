% Usage: rule_application_target(+RuleName, ?Graph)
% Finds the resulting graph after an application of the given rule.
rule_application_target(RuleName, Graph) :-
  state(Source), % Get a source state in the LTS
  state_transition(Source, Transition), % Get a transition from source state
  edge_label(Transition, RuleName), % Test whether the transition label is the one we seek
  transition_target(Transition, Target), % Get the target state from the transition
  state_graph(Target, Graph)  % Get the graph of target state
  .

print_mosq_count(Graph) :-
  label('nmosquito', Label),
  node_with_attribute(Graph, Node, Label, Value),
  write('Total de mosquitos: '), write(Value), nl.

print_ovos_count(Graph) :-
  label('novos', Label),
  node_with_attribute(Graph, Node, Label, Value),
  write('Total de ovos: '), write(Value), nl.

run :-
  rule_application_target('diamosquit', Graph),
  print_mosq_count(Graph),
  print_ovos_count(Graph)
  .