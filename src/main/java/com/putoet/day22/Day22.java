package com.putoet.day22;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

import java.util.List;

public class Day22 {
    public static void main(String[] args) {
        final var nodes = DfParser.parse(ResourceLines.list("/day22.txt"));

        Timer.run(() -> System.out.println("Number of viable pairs: " + viablePairs(nodes)));

        // Part 2 was solved by hand using the printed grid of nodes ... The puzzle might be solvable by a program
        // but it requires a lot of work to make it generic (if even possible). Beware, the grid contain nodes which
        // are too big to move. You could consider this puzzle a maze where first you need to move the empty node
        // next to the goal node. The second step is to move the goal node to the target (swap the goal node with the
        // empty node, and move the empty node in front of the target node again)
        //
        // The target node was printed as (.)
        // The goal node (to fetch the data from) as G
        // The empty node as [_]
        // A movable node as .
        // and an unmovable node as #
        //
        // Simply count the number of steps the empty node needs to take before its in front of the goal node.
        // Then add the number of steps the goal node needs to take to move next to the target node (times 5, as the
        // empty node also needs to get in front of the target node again)
        // And finally, add one as the goal node needs a final swap to get at the target position
        final NodeGrid grid = new NodeGrid(nodes);
        grid.print();

    }

    private static long viablePairs(List<Node> nodes) {
        return nodes.stream()
                .filter(node -> node.used() > 0)
                .mapToLong(node -> nodes.stream()
                            .filter(other -> !other.equals(node) && other.free() >= node.used())
                            .count()
                )
                .sum();
    }
}
