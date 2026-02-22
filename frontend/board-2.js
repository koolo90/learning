const container = document.getElementById("honeycomb");
const hexTemplate = document.getElementById("hex-template");
const playBtn = document.getElementById("play-btn");
const pauseBtn = document.getElementById("pause-btn");
const resetBtn = document.getElementById("reset-btn");

const side = 62.5;
const gap = 10;
const hexWidth = side * 1.732;
const factor = (hexWidth + gap) / hexWidth;
const rowDx = (hexWidth / 2) * factor;
const rowGap = (side * 1.5 * factor) - (side * 2);
const rowHeight = (side * 2) + rowGap;
const rowStride = (hexWidth + gap);

const rows = Math.ceil(container.clientHeight / rowHeight) + 3;
const cols = Math.ceil(container.clientWidth / rowStride) + 3;
const hexHeight = side * 2;
const baseColor = "#00AA00";
const activeColor = "#0000FF";
const tickMs = 200;
const hexMap = new Map();
let timerId = null;

function setHexColor(hex, color) {
    const top = hex.querySelector(".top");
    const middle = hex.querySelector(".middle");
    const bottom = hex.querySelector(".bottom");

    if (top) top.style.backgroundColor = color;
    if (middle) middle.style.backgroundColor = color;
    if (bottom) bottom.style.backgroundColor = color;
}

function setAlive(hex, isAlive) {
    hex.dataset.alive = isAlive ? "1" : "0";
    if (isAlive) {
        hex.classList.remove("is-forest");
        setHexColor(hex, activeColor);
    } else {
        hex.classList.add("is-forest");
        setHexColor(hex, baseColor);
    }
}

function toggleHexColor(hex) {
    const isAlive = hex.dataset.alive === "1";
    setAlive(hex, !isAlive);
}

function getNeighbors(rowIndex, colIndex) {
    const isOdd = rowIndex % 2 === 1;
    const deltas = isOdd
        ? [[-1, 0], [-1, 1], [0, -1], [0, 1], [1, 0], [1, 1]]
        : [[-1, -1], [-1, 0], [0, -1], [0, 1], [1, -1], [1, 0]];

    return deltas.map(([dr, dc]) => `${rowIndex + dr},${colIndex + dc}`);
}

function stepSimulation() {
    const nextStates = new Map();
    hexMap.forEach((hex, key) => {
        const [rowIndex, colIndex] = key.split(",").map(Number);
        const neighbors = getNeighbors(rowIndex, colIndex);
        let aliveCount = 0;
        for (const nKey of neighbors) {
            const neighbor = hexMap.get(nKey);
            if (neighbor && neighbor.dataset.alive === "1") {
                aliveCount++;
            }
        }
        const isAlive = hex.dataset.alive === "1";
        const willLive = isAlive
            ? (aliveCount === 2 || aliveCount === 3)
            : (aliveCount === 3);
        nextStates.set(key, willLive);
    });

    nextStates.forEach((willLive, key) => {
        const hex = hexMap.get(key);
        if (hex) setAlive(hex, willLive);
    });
}

function play() {
    if (timerId !== null) return;
    timerId = setInterval(stepSimulation, tickMs);
}

function pause() {
    if (timerId === null) return;
    clearInterval(timerId);
    timerId = null;
}

function resetAnimation() {
    const hexes = container.querySelectorAll(".hex-cnt");
    hexes.forEach((hex) => {
        setAlive(hex, false);
    });
    pause();
}

let isDragging = false;

function paintFromEvent(event) {
    const target = event.target.closest(".hex-cnt");
    if (target) {
        setAlive(target, true);
    }
}

playBtn.addEventListener("click", play);
pauseBtn.addEventListener("click", pause);
resetBtn.addEventListener("click", resetAnimation);

container.addEventListener("mousedown", (event) => {
    if (event.button !== 0) {
        return;
    }
    isDragging = true;
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
});

container.addEventListener("mouseleave", () => {
    isDragging = false;
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
            setAlive(hex, false);
            hex.addEventListener("click", () => toggleHexColor(hex));
            hex.style.left = `${x}px`;
            hex.style.top = `${y}px`;
            container.appendChild(hex);
        }
    }
}