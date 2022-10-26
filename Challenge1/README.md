# Challenge 1

## Droidcon 2022 – Bumble Coding Challenge 

### Task description

You have been handed over an Appyx project but something is not working. Your goal is to fix the project
to make it match the specification. To help you identify the problematic places we have left `TODO`s.

### Important 

- Please read the documentation carefully as it will help you get the challenge done quicker :) 
- Don't forget to turn on animations on your device!
- You can check how the final result should look like at the bottom of this page

### Project requirements

1. Clicking on the `Push` button pushes a new element to the `BackStack` with cross-fade animation.
2. Clicking on the `Pop` button pops the `BackStack` with cross-fade and explosion:
    - currently shown element fades out + scales up (scale = `2f`).

### Documentation

This [quick start guide](https://bumble-tech.github.io/appyx/how-to-use-appyx/quick-start/) should
help you quickly get on board with Appyx but feel free to check the full documentation.  

In a nutshell, the key pieces are:

1. [NavModel](https://bumble-tech.github.io/appyx/navmodel/) – responsible for managing the state of the elements.
2. [Children](https://bumble-tech.github.io/appyx/ui/children-view/) – composable function which draws children in a single container.
3. [Transition handler](https://bumble-tech.github.io/appyx/ui/transitions/#appyx-transition-handlers/) – defines how changing the state of elements results in transitions.
4. [BackStack](https://bumble-tech.github.io/appyx/navmodel/backstack/) – a classic `NavModel` implementation which is used in this challenge 

### Final result

If you did it right your final result should look like this:

<img src="https://i.imgur.com/wxDqgGe.gif" width="270" height="586">
