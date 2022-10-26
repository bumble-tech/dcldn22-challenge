#  Challenge 2 

## Droidcon 2022 – Bumble Coding Challenge 

### Task description

You have been handed over an Appyx project but something is not working. Your goal is to fix the project
to make it match the specification. To help you identify the problematic places we have left TODOs.


### Important

- ⚠️⚠️ **Note that the documentation for this challenge is slightly different than challenge 1** ⚠️⚠️
- Please read the documentation carefully as it will help you get the challenge done quicker :) 
- Don't forget to turn on animations on your device!
- You can check how the final result should look like at the bottom of this page


### Project requirements

1. Clicking on the `Push` button pushes a new element to the `BackStack`. Transition for this operation already works as expected.
2. Clicking on the `Pop` button pops the `BackStack`. Transition for this operation already works as expected.
3. Clicking on `Freeze` performs `ToggleFreeze` operation. This operation toggles the state of currently active element and it becomes either `Frozen` or `Active`.
When it becomes `Frozen`, blur with radius 15.dp is applied, blocking `Push` and `Pop` operations. Text of the button changes to `Unfreeze`.
When it becomes `Active`, blue is disappeared. `Push` and `Pop` operations get unblocked. Text of the button changes to `Freeze`.


### Documentation

In addition to **Challenge 1**, there's one new concept:

1. [Custom NavModel](https://bumble-tech.github.io/appyx/navmodel/custom/) - in this example we're using a custom `NavModel` implementation.
This resource could help you understand how custom `NavModels` are implemented


## Final result

If you did it right your final result should look like this:

<img src="https://i.imgur.com/P9NBuij.gif" width="270" height="586">
