#ifndef IDLESTATE_H
#define IDLESTATE_H

/*
 * TODO: For lab:
 * - implement the two override functions of GameState, printOptions and handleInput
 * - implement constructor to remember the status of the previous travel state
 */

class IdleState {
  private:
    enum Choices {
      CONTINUE_OPTION = 1,
      LEAVE_OPTION    = 2
    };
};

#endif
