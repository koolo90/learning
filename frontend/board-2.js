const container = document.getElementById("honeycomb");
const hexTemplate = document.getElementById("hex-template");
const playBtn = document.getElementById("play-btn");
const pauseBtn = document.getElementById("pause-btn");
const resetBtn = document.getElementById("reset-btn");

const cellStates = {
    DEAD: "dead",
    BORNING: "borning",
    ALIVE: "alive",
    DYING: "dying",
};

const cellStatesColor = {
    [cellStates.DEAD]: "#ccFFcc",
    [cellStates.BORNING]: "#FFaaFF",
    [cellStates.ALIVE]: "#00FF77",
    [cellStates.DYING]: "#FF0000",
};

const side = 25;
const gap = 2;
const hexWidth = side * 1.732;
const factor = (hexWidth + gap) / hexWidth;
const rowDx = (hexWidth / 2) * factor;
const rowGap = (side * 1.5 * factor) - (side * 2);
const rowHeight = (side * 2) + rowGap;
const rowStride = (hexWidth + gap);

const rows = Math.ceil(container.clientHeight / rowHeight) + 3;
const cols = Math.ceil(container.clientWidth / rowStride) + 3;
const hexHeight = side * 2;
const tickMs = 500;
const hexMap = new Map();
let timerId = null;
let isPreviewPhase = true;

function setHexColor(hex, cellState) {
    const top = hex.querySelector(".top");
    const middle = hex.querySelector(".middle");
    const bottom = hex.querySelector(".bottom");
    const color = cellStatesColor[cellState] ?? cellStatesColor[cellStates.DEAD];

    if (top) top.style.backgroundColor = color;
    if (middle) middle.style.backgroundColor = color;
    if (bottom) bottom.style.backgroundColor = color;
}

function setAlive(hex, cellState) {
    hex.dataset.state = cellState;
    setHexColor(hex, cellState);
}

function toggleHexColor(hex) {
    const currentState = hex.dataset.state ?? cellStates.DEAD;
    const isAlive = currentState === cellStates.ALIVE || currentState === cellStates.DYING;
    setAlive(hex, isAlive ? cellStates.DEAD : cellStates.ALIVE);
}

function getNeighbors(rowIndex, colIndex) {
    const isOdd = rowIndex % 2 === 1;
    const deltas = isOdd
        ? [[-1, 0], [-1, 1], [0, -1], [0, 1], [1, 0], [1, 1]]
        : [[-1, -1], [-1, 0], [0, -1], [0, 1], [1, -1], [1, 0]];

    return deltas.map(([dr, dc]) => `${rowIndex + dr},${colIndex + dc}`);
}

function stepSimulation() {
    if (!isPreviewPhase) {
        hexMap.forEach((hex) => {
            const currentState = hex.dataset.state ?? cellStates.DEAD;
            if (currentState === cellStates.BORNING) {
                setAlive(hex, cellStates.ALIVE);
            } else if (currentState === cellStates.DYING) {
                setAlive(hex, cellStates.DEAD);
            }
        });
        isPreviewPhase = true;
        return;
    }

    const nextStates = new Map();
    hexMap.forEach((hex, key) => {
        const [rowIndex, colIndex] = key.split(",").map(Number);
        const neighbors = getNeighbors(rowIndex, colIndex);
        let aliveCount = 0;
        for (const nKey of neighbors) {
            const neighbor = hexMap.get(nKey);
            const neighborState = neighbor?.dataset.state ?? cellStates.DEAD;
            if (neighbor && (neighborState === cellStates.ALIVE || neighborState === cellStates.DYING)) {
                aliveCount++;
            }
        }
        const currentState = hex.dataset.state ?? cellStates.DEAD;
        const isAlive = currentState === cellStates.ALIVE || currentState === cellStates.DYING;
        const willLive = isAlive
            ? (aliveCount === 2 || aliveCount === 3)
            : (aliveCount === 3);
        nextStates.set(key, willLive);
    });

    nextStates.forEach((willLive, key) => {
        const hex = hexMap.get(key);
        if (!hex) {
            return;
        }
        const currentState = hex.dataset.state ?? cellStates.DEAD;
        const isAlive = currentState === cellStates.ALIVE || currentState === cellStates.DYING;
        if (isAlive && !willLive) {
            setAlive(hex, cellStates.DYING);
        } else if (!isAlive && willLive) {
            setAlive(hex, cellStates.BORNING);
        } else {
            setAlive(hex, isAlive ? cellStates.ALIVE : cellStates.DEAD);
        }
    });
    isPreviewPhase = false;
}

function play() {
    if (timerId !== null) {
        return;
    }

    playBtn.innerText = String.fromCodePoint(10074) +  String.fromCodePoint(10074);

    playBtn.removeEventListener("click", play);
    playBtn.addEventListener("click", pause);
    timerId = setInterval(stepSimulation, tickMs);
}

function pause() {
    if (timerId === null) {
        return;
    }

    playBtn.innerText = String.fromCodePoint(9654);
    playBtn.removeEventListener("click", pause);
    playBtn.addEventListener("click", play);
    clearInterval(timerId);
    timerId = null;
}

function resetAnimation() {
    const hexes = container.querySelectorAll(".hex-cnt");
    hexes.forEach((hex) => {
        setAlive(hex, cellStates.DEAD);
    });
    isPreviewPhase = true;
    pause();
}

let isDragging = false;
let didDragPaint = false;

function paintFromEvent(event) {
    const target = event.target.closest(".hex-cnt");
    if (target) {
        setAlive(target, cellStates.ALIVE);
        didDragPaint = true;
    }
}

playBtn.addEventListener("click", play);
resetBtn.addEventListener("click", resetAnimation);

container.addEventListener("mousedown", (event) => {
    if (event.button !== 0) {
        return;
    }
    isDragging = true;
    didDragPaint = false;
    paintFromEvent(event);
});

container.addEventListener("mouseover", (event) => {
    if (!isDragging || event.buttons !== 1) {
        return;
    }
    paintFromEvent(event);
});

container.addEventListener("mouseup", () => {
    isDragging = false;
    didDragPaint = false;
});

container.addEventListener("mouseleave", () => {
    isDragging = false;
    didDragPaint = false;
});

for (let r = 0; r < rows; r++) {
    const xOffset = (r % 2 === 1) ? rowDx : 0;
    const y = r * rowHeight;

    for (let c = 0; c < cols; c++) {
        const x = c * rowStride + xOffset;
        if (x >= 0 && y >= 0 &&
            (x + hexWidth) <= container.clientWidth &&
            (y + hexHeight) <= container.clientHeight) {
            const hex = hexTemplate.content.firstElementChild.cloneNode(true);
            hex.dataset.row = String(r);
            hex.dataset.col = String(c);
            hexMap.set(`${r},${c}`, hex);
            setAlive(hex, cellStates.DEAD);
            hex.addEventListener("click", () => {
                if (didDragPaint) {
                    didDragPaint = false;
                    return;
                }
                toggleHexColor(hex);
            });
            hex.style.left = `${x}px`;
            hex.style.top = `${y}px`;
            container.appendChild(hex);
        }
    }
}
